package org.realrest.domain;

import java.util.Date;

/**
 * @author volodymyr.tsukur
 */
public final class Booking extends Identifiable {

    private User user;

    private Room room;

    private Date from;

    private Date to;

    private boolean includeBreakfast;

    private State state;

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(final Room room) {
        this.room = room;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(final Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(final Date to) {
        this.to = to;
    }

    public boolean isIncludeBreakfast() {
        return includeBreakfast;
    }

    public void setIncludeBreakfast(final boolean includeBreakfast) {
        this.includeBreakfast = includeBreakfast;
    }

    public State getState() {
        return state;
    }

    public void setState(final State state) {
        this.state = state;
    }

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
