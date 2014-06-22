package org.realrest.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public final class Booking extends Identifiable {

    private User user;

    private Room room;

    private LocalDate from;

    private LocalDate to;

    private boolean includeBreakfast;

    private State state;

    /**
     * @author volodymyr.tsukur
     */
    public enum State {

        CREATED,

        PAYMENT_SUBMITTED,

        PAYMENT_CONFIRMED,

        SERVED,

        CANCELLED

    }

}
