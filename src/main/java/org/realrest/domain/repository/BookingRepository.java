package org.realrest.domain.repository;

import org.realrest.domain.Booking;
import org.realrest.domain.BookingNotFoundException;

/**
 * @author volodymyr.tsukur
 */
public interface BookingRepository {

    Booking create(Booking data);

    Booking findById(Long id) throws BookingNotFoundException;

}
