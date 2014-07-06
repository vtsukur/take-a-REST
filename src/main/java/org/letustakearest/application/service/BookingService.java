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

    Booking update(Long id, UpdateBookingTransition transition) throws EntityNotFoundException;

    Booking findById(Long id) throws EntityNotFoundException;

    Collection<Booking> findAll();

    Booking pay(Long id, PayForBookingTransition data) throws EntityNotFoundException;

    void delete(Long id);

}
