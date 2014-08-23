package org.takearest.application.service.impl;

import org.takearest.application.service.BookingService;
import org.takearest.application.service.PaymentGateway;
import org.takearest.domain.Booking;
import org.takearest.domain.EntityNotFoundException;
import org.takearest.domain.Payment;
import org.takearest.domain.Place;
import org.takearest.domain.repository.BookingRepository;
import org.takearest.domain.repository.PaymentRepository;
import org.takearest.domain.repository.PlaceRepository;
import org.takearest.presentation.transitions.*;

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
    public Booking create(final CreateBookingTransition transition) throws EntityNotFoundException {
        validation.validate(transition, "create-booking");

        final Place place = placeRepository.findById(transition.getRoomId());
        final Booking booking = map(new Booking(), transition.getData());
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
    public Booking update(final Booking booking, final UpdateBookingTransition transition) {
        final Booking mappedBooking = map(booking, transition.getData());
        bookingRepository.update(mappedBooking);
        return mappedBooking;
    }

    private Booking map(final Booking booking, final BookingData bookingData) {
        if (bookingData != null) {
            if (bookingData.getFrom() != null) {
                booking.setFrom(bookingData.getFrom());
            }
            if (bookingData.getTo() != null) {
                booking.setTo(bookingData.getTo());
            }
            if (bookingData.getIncludeBreakfast() != null) {
                booking.setIncludeBreakfast(bookingData.getIncludeBreakfast());
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
                Period.between(booking.getFrom(), booking.getTo()).getDays());

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
