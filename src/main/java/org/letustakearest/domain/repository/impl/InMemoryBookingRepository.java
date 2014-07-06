package org.letustakearest.domain.repository.impl;

import org.letustakearest.domain.Booking;
import org.letustakearest.domain.repository.BookingRepository;

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
