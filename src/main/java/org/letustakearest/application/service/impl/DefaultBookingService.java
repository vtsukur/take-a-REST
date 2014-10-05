package org.letustakearest.application.service.impl;

import org.letustakearest.application.service.BookingService;
import org.letustakearest.application.service.PaymentGateway;
import org.letustakearest.domain.Booking;
import org.letustakearest.domain.EntityNotFoundException;
import org.letustakearest.domain.Payment;
import org.letustakearest.domain.Place;
import org.letustakearest.domain.repository.BookingRepository;
import org.letustakearest.domain.repository.PaymentRepository;
import org.letustakearest.domain.repository.PlaceRepository;
import org.letustakearest.presentation.transitions.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Period;
import java.util.Collection;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class DefaultBookingService implements BookingService {

    @Inject
    private BookingRepository bookingRepository;

    @Inject
    private PlaceRepository placeRepository;

    @Inject
    private PaymentRepository paymentRepository;

    @Inject
    private PaymentGateway paymentGateway;

    @Inject
    private Validation validation;

    @Override
    public Booking create(final Long roomId, final BookingTransition transition) throws EntityNotFoundException {
        validation.validate(transition, "create-booking");

        final Place place = placeRepository.findById(roomId);
        final Booking booking = map(new Booking(), transition);
        booking.setState(Booking.State.CREATED);
        booking.setPlace(place);
        return bookingRepository.create(booking);
    }

    @Override
    public Booking create(CreateBookingAsPlaceTransition transition) throws EntityNotFoundException {
        validation.validate(transition, "create-booking-as-place");

        final Place place = placeRepository.findById(transition.getPlaceId());
        final Booking booking = map(new Booking(), transition.getData());
        booking.setState(Booking.State.CREATED);
        booking.setPlace(place);
        return bookingRepository.create(booking);
    }

    @Override
    public Booking update(final Booking booking, final BookingTransition transition) throws EntityNotFoundException {
        validation.validate(transition, "update-booking");

        final Place place = placeRepository.findById(booking.getPlace().getId());
        final Booking mappedBooking = map(booking, transition);
        booking.setPlace(place);
        bookingRepository.update(mappedBooking);
        return mappedBooking;
    }

    private Booking map(final Booking booking, final BookingTransition bookingTransition) {
        if (bookingTransition != null) {
            if (bookingTransition.getCheckIn() != null) {
                booking.setCheckIn(bookingTransition.getCheckIn());
            }
            if (bookingTransition.getCheckOut() != null) {
                booking.setCheckOut(bookingTransition.getCheckOut());
            }
            if (bookingTransition.getIncludeBreakfast() != null) {
                booking.setIncludeBreakfast(bookingTransition.getIncludeBreakfast());
            }
        }
        return booking;
    }

    @Override
    public Booking findById(final Long id) throws EntityNotFoundException {
        return bookingRepository.findById(id);
    }

    @Override
    public Collection<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking pay(final Booking booking, final PayForBookingTransition data) {
        Payment payment = new Payment();
        payment.setCardholdersName(data.getCardholdersName());
        payment.setCreditCardNumber(data.getCreditCardNumber());
        payment.setAmount(booking.getPlace().getPrice() *
                Period.between(booking.getCheckIn(), booking.getCheckOut()).getDays());

        payment = paymentRepository.create(payment);
        booking.setPayment(payment);
        payment.setBooking(booking);

        paymentGateway.process(payment);

        booking.setState(Booking.State.PAID);
        bookingRepository.update(booking);

        return booking;
    }

    @Override
    public void delete(final Long id) {
        bookingRepository.delete(id);
    }

}
