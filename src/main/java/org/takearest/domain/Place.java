package org.takearest.domain;

import lombok.Getter;

/**
 * @author volodymyr.tsukur
 */
@Getter
public final class Place extends IdentifiableAndVersioned {

    private Type type;

    private Category category;

    private Integer price;

    public Place(Category category, Integer price) {
        this(Type.ROOM, category, price);
    }

    public Place(Type type, Category category, Integer price) {
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
