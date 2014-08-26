package org.letustakearest.application.service;

import org.letustakearest.domain.Booking;
import org.letustakearest.domain.EntityNotFoundException;
import org.letustakearest.presentation.transitions.BookingTransition;
import org.letustakearest.presentation.transitions.CreateBookingAsPlaceTransition;
import org.letustakearest.presentation.transitions.PayForBookingTransition;

import java.util.Collection;

/**
 * @author volodymyr.tsukur
 */
public interface BookingService {

    Booking create(Long roomId, BookingTransition data) throws EntityNotFoundException;

    Booking create(CreateBookingAsPlaceTransition data) throws EntityNotFoundException;

    Booking update(Booking booking, BookingTransition data) throws EntityNotFoundException;

    Booking findById(Long id) throws EntityNotFoundException;

    Collection<Booking> findAll();

    Booking pay(Booking booking, PayForBookingTransition data);

    void delete(Long id);

}
