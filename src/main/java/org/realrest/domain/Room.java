package org.realrest.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public final class Room extends Identifiable {

    private Hotel hotel;

    private Type type;

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
