package org.realrest.domain;

/**
 * @author volodymyr.tsukur
 */
public class Room extends Identifiable {

    private Hotel hotel;

    private Type type;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(final Hotel hotel) {
        this.hotel = hotel;
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public enum Type {

        SINGLE,

        DOUBLE,

        TRIPLE,

        QUAD,

        QUEEN,

        KING,

        TWIN,

        DOUBLE_DOUBLE,

        JUNIOR_SUITE,

        SUITE

    }

}
