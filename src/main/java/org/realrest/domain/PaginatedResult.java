package org.realrest.domain;

import lombok.Getter;
import org.realrest.application.service.Pagination;

import java.util.List;

/**
 * @author volodymyr.tsukur
 */
@Getter
public final class PaginatedResult<E extends Identifiable> {

    private final List<E> list;

    private final Pagination actualPagination;

    private final int total;

    public PaginatedResult(final List<E> list, final Pagination actualPagination, final int total) {
        this.list = list;
        this.actualPagination = actualPagination;
        this.total = total;
    }

}
