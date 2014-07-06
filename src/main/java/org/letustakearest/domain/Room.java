package org.letustakearest.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public final class Room extends Identifiable {

    private Type type;

    private Integer price;

    public Room(Type type, Integer price) {
        this.type = type;
        this.price = price;
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
