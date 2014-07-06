package org.letustakearest.application.service;

import lombok.Getter;

/**
 * @author volodymyr.tsukur
 */
@Getter
public final class Pagination {

    private int offset;

    private int limit;

    public Pagination(final int offset, final int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public static final Pagination DEFAULT = new Pagination(0, 2);

    public static Pagination getPagination(final Integer offset, final Integer limit) {
        return new Pagination(offset == null ? DEFAULT.offset : offset, limit == null ? DEFAULT.limit : limit);
    }

}
