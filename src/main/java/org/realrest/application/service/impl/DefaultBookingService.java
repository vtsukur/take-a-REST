package org.realrest.application.service.impl;

import org.realrest.domain.Booking;
import org.realrest.domain.repository.BookingRepository;
import org.realrest.application.service.BookingService;
import org.realrest.presentation.transitions.CreateBookingTransition;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class DefaultBookingService implements BookingService {

    @Inject
    private BookingRepository bookingRepository;

    @Override
    public Booking create(final CreateBookingTransition data) {
        final Booking booking = new Booking();
        booking.setFrom(data.getFrom());
        booking.setTo(data.getTo());
        booking.setIncludeBreakfast(data.isIncludeBreakfast());
        booking.setState(Booking.State.PENDING);
        return bookingRepository.create(booking);
    }

    @Override
    public Booking findById(final Long id) {
        return bookingRepository.findById(id);
    }

}
