package org.realrest.application.service.impl;

import org.realrest.application.service.BookingService;
import org.realrest.application.service.PaymentGateway;
import org.realrest.domain.Booking;
import org.realrest.domain.EntityNotFoundException;
import org.realrest.domain.Payment;
import org.realrest.domain.Room;
import org.realrest.domain.repository.BookingRepository;
import org.realrest.domain.repository.PaymentRepository;
import org.realrest.domain.repository.RoomRepository;
import org.realrest.presentation.transitions.BookingData;
import org.realrest.presentation.transitions.CreateBookingTransition;
import org.realrest.presentation.transitions.PayForBookingTransition;
import org.realrest.presentation.transitions.UpdateBookingTransition;

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
    private RoomRepository roomRepository;

    @Inject
    private PaymentRepository paymentRepository;

    @Inject
    private PaymentGateway paymentGateway;

    @Override
    public Booking create(final CreateBookingTransition transition) throws EntityNotFoundException {
        final Room room = roomRepository.findById(transition.getRoomId());
        final Booking booking = map(new Booking(), transition.getData());
        booking.setState(Booking.State.CREATED);
        booking.setRoom(room);
        return bookingRepository.create(booking);
    }

    @Override
    public Booking update(final Long id, final UpdateBookingTransition transition) throws EntityNotFoundException {
        final Booking booking = map(findById(id), transition.getData());
        bookingRepository.update(booking);
        return booking;
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
    public Booking pay(final Long id, final PayForBookingTransition data) throws EntityNotFoundException {
        final Booking booking = findById(id);

        Payment payment = new Payment();
        payment.setCardholdersName(data.getCardholdersName());
        payment.setCreditCardNumber(data.getCreditCardNumber());
        payment.setAmount(booking.getRoom().getPrice() *
                Period.between(booking.getFrom(), booking.getTo()).getDays());

        payment = paymentRepository.create(payment);
        booking.setPayment(payment);
        payment.setBooking(booking);

        paymentGateway.process(payment);

        booking.setState(Booking.State.PAID);

        return booking;
    }

    @Override
    public void delete(final Long id) {
        bookingRepository.delete(id);
    }

}
