package org.realrest.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public final class Booking extends Identifiable {

    private User user;

    private Room room;

    private Date from;

    private Date to;

    private boolean includeBreakfast;

    private State state;

    /**
     * @author volodymyr.tsukur
     */
    public enum State {

        PENDING,

        CONFIRMED,

        CANCELLED,

        SERVED,

        REJECTED

    }

}
