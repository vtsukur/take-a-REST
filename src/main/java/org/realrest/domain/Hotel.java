package org.realrest.domain;

import java.util.Set;

/**
 * @author volodymyr.tsukur
 */
public class Hotel extends Identifiable {

    private Set<Room> rooms;

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(final Set<Room> rooms) {
        this.rooms = rooms;
    }

}
