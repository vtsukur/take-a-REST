package org.realrest.application.service;

import org.realrest.domain.Booking;
import org.realrest.domain.EntityNotFoundException;
import org.realrest.presentation.transitions.CreateBookingTransition;
import org.realrest.presentation.transitions.PayForBookingTransition;

import java.util.Collection;

/**
 * @author volodymyr.tsukur
 */
public interface BookingService {

    Booking create(CreateBookingTransition data) throws EntityNotFoundException;

    Booking findById(Long id) throws EntityNotFoundException;

    Collection<Booking> findAll();

    Booking pay(Long id, PayForBookingTransition data) throws EntityNotFoundException;

    void delete(Long id);
    
}
