package org.takearest.application.service;

import org.takearest.domain.Booking;
import org.takearest.domain.EntityNotFoundException;
import org.takearest.presentation.transitions.CreateBookingAsPlaceTransition;
import org.takearest.presentation.transitions.CreateBookingTransition;
import org.takearest.presentation.transitions.PayForBookingTransition;
import org.takearest.presentation.transitions.UpdateBookingTransition;

import java.util.Collection;

/**
 * @author volodymyr.tsukur
 */
public interface BookingService {

    Booking create(CreateBookingTransition data) throws EntityNotFoundException;

    Booking create(CreateBookingAsPlaceTransition data) throws EntityNotFoundException;

    Booking update(Booking booking, UpdateBookingTransition transition);

    Booking findById(Long id) throws EntityNotFoundException;

    Collection<Booking> findAll();

    Booking pay(Booking booking, PayForBookingTransition data);

    void delete(Long id);

}
