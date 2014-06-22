package org.realrest.domain.repository;

import org.realrest.domain.Booking;

/**
 * @author volodymyr.tsukur
 */
public interface BookingRepository {

    Booking create(Booking data);

    Booking findById(Long id);

}
