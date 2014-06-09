package org.realrest.domain;

import java.util.Set;

/**
 * @author volodymyr.tsukur
 */
public final class User extends Identifiable {

    private Set<Booking> bookings;

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(final Set<Booking> bookings) {
        this.bookings = bookings;
    }

}
