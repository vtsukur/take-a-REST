package org.realrest.domain.service;

import org.realrest.domain.Booking;
import org.realrest.infrastructure.rest.jaxrs.transitions.CreateBookingTransition;

/**
 * @author volodymyr.tsukur
 */
public interface BookingService {

    Booking create(CreateBookingTransition data);

    Booking findById(Long id);

}
