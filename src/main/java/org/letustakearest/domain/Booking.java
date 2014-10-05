package org.letustakearest.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public final class Booking extends IdentifiableAndVersioned {

    private User user;

    private Place place;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private boolean includeBreakfast;

    private BigDecimal price;

    private State state;

    private Payment payment;

    /**
     * @author volodymyr.tsukur
     */
    public enum State {

        CREATED,

        PAID,

        SERVED,

        CANCELLED

    }

}
