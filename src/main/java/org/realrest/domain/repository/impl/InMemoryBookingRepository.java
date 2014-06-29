package org.realrest.domain.repository.impl;

import org.realrest.domain.Booking;
import org.realrest.domain.repository.BookingRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class InMemoryBookingRepository extends BaseInMemoryBookingRepository<Booking> implements BookingRepository {

    protected InMemoryBookingRepository() {
        super("Booking");
    }

}
