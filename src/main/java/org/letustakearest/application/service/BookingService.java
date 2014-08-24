package org.letustakearest.application.service;

import org.letustakearest.domain.Booking;
import org.letustakearest.domain.EntityNotFoundException;
import org.letustakearest.presentation.transitions.CreateBookingAsPlaceTransition;
import org.letustakearest.presentation.transitions.PayForBookingTransition;
import org.letustakearest.presentation.transitions.SetBookingTransition;

import java.util.Collection;

/**
 * @author volodymyr.tsukur
 */
public interface BookingService {

    Booking create(SetBookingTransition data) throws EntityNotFoundException;

    Booking create(CreateBookingAsPlaceTransition data) throws EntityNotFoundException;

    Booking update(Booking booking, SetBookingTransition transition) throws EntityNotFoundException;

    Booking findById(Long id) throws EntityNotFoundException;

    Collection<Booking> findAll();

    Booking pay(Booking booking, PayForBookingTransition data);

    void delete(Long id);

}
