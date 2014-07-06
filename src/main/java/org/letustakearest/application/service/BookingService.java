package org.letustakearest.application.service;

import org.letustakearest.domain.Booking;
import org.letustakearest.domain.EntityNotFoundException;
import org.letustakearest.presentation.transitions.CreateBookingTransition;
import org.letustakearest.presentation.transitions.PayForBookingTransition;
import org.letustakearest.presentation.transitions.UpdateBookingTransition;

import java.util.Collection;

/**
 * @author volodymyr.tsukur
 */
public interface BookingService {

    Booking create(CreateBookingTransition data) throws EntityNotFoundException;

    Booking update(Booking booking, UpdateBookingTransition transition);

    Booking findById(Long id) throws EntityNotFoundException;

    Collection<Booking> findAll();

    Booking pay(Booking booking, PayForBookingTransition data);

    void delete(Long id);

}
