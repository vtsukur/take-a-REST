package org.letustakearest.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public final class Booking extends IdentifiableAndVersioned {

    private User user;

    private Room room;

    private LocalDate from;

    private LocalDate to;

    private boolean includeBreakfast;

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
