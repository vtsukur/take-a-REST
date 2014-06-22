package org.realrest.application.service;

import org.realrest.domain.Booking;
import org.realrest.domain.BookingNotFoundException;
import org.realrest.presentation.transitions.CreateBookingTransition;

/**
 * @author volodymyr.tsukur
 */
public interface BookingService {

    Booking create(CreateBookingTransition data);

    Booking findById(Long id) throws BookingNotFoundException;

}
