package org.realrest.application.service.impl;

import org.realrest.application.service.BookingService;
import org.realrest.application.service.PaymentGateway;
import org.realrest.domain.Booking;
import org.realrest.domain.EntityNotFoundException;
import org.realrest.domain.Payment;
import org.realrest.domain.repository.BookingRepository;
import org.realrest.domain.repository.PaymentRepository;
import org.realrest.presentation.transitions.CreateBookingTransition;
import org.realrest.presentation.transitions.PayForBookingTransition;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class DefaultBookingService implements BookingService {

    @Inject
    private BookingRepository bookingRepository;

    @Inject
    private PaymentRepository paymentRepository;

    @Inject
    private PaymentGateway paymentGateway;

    @Override
    public Booking create(final CreateBookingTransition data) {
        final Booking booking = new Booking();
        booking.setFrom(data.getFrom());
        booking.setTo(data.getTo());
        booking.setIncludeBreakfast(data.isIncludeBreakfast());
        booking.setState(Booking.State.CREATED);
        return bookingRepository.create(booking);
    }

    @Override
    public Booking findById(final Long id) throws EntityNotFoundException {
        return bookingRepository.findById(id);
    }

    @Override
    public Booking pay(final Long id, final PayForBookingTransition data) throws EntityNotFoundException {
        final Booking booking = findById(id);

        Payment payment = new Payment();
        payment.setCardholdersName(data.getCardholdersName());
        payment.setCreditCardNumber(data.getCreditCardNumber());
        payment.setAmount(1000); // TODO take amount from room price

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
