package org.takearest.domain.repository.impl;

import org.takearest.domain.Booking;
import org.takearest.domain.repository.BookingRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class InMemoryBookingRepository extends BaseInMemoryRepository<Booking> implements BookingRepository {

    protected InMemoryBookingRepository() {
        super("Booking");
    }

}
