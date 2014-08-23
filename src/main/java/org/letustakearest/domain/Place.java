package org.letustakearest.domain;

import lombok.Getter;

/**
 * @author volodymyr.tsukur
 */
@Getter
public final class Place extends IdentifiableAndVersioned {

    private Hotel hotel;

    private Type type;

    private Category category;

    private Integer price;

    public Place(Hotel hotel, Category category, Integer price) {
        this(hotel, Type.ROOM, category, price);
    }

    public Place(Hotel hotel, Type type, Category category, Integer price) {
        this.hotel = hotel;
        this.type = type;
        this.category = category;
        this.price = price;
    }

    public enum Type {

        ROOM,

        APARTMENT

    }

    public enum Category {

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
