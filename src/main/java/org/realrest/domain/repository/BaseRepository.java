package org.realrest.domain.repository;

import org.realrest.application.service.Pagination;
import org.realrest.domain.EntityNotFoundException;
import org.realrest.domain.Identifiable;
import org.realrest.domain.PaginatedResult;

import java.util.Collection;

/**
 * @author volodymyr.tsukur
 */
public interface BaseRepository<E extends Identifiable> {

    E create(E data);

    E findById(Long id) throws EntityNotFoundException;

    Collection<E> findAll();

    PaginatedResult<E> findSeveral(Pagination pagination);

    void delete(Long id);

}
