package org.realrest.domain.repository.impl;

import org.realrest.domain.Booking;
import org.realrest.domain.BookingNotFoundException;
import org.realrest.domain.repository.BookingRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class InMemoryBookingRepository implements BookingRepository {

    private final Map<Long, Booking> bookingMap = new ConcurrentHashMap<>();

    private final AtomicLong incrementalId = new AtomicLong(0);

    @Override
    public Booking create(final Booking data) {
        final Long id = incrementalId.incrementAndGet();
        data.setId(id);
        bookingMap.put(id, data);
        return data;
    }

    @Override
    public Booking findById(final Long id) throws BookingNotFoundException {
        final Booking result = bookingMap.getOrDefault(id, null);
        if (result != null) {
            return result;
        }
        else {
            throw new BookingNotFoundException(String.format("Booking with id %d not found", id));
        }
    }

}
