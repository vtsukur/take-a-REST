package org.takearest.domain.repository;

import org.takearest.application.service.Pagination;
import org.takearest.domain.EntityNotFoundException;
import org.takearest.domain.IdentifiableAndVersioned;
import org.takearest.domain.PaginatedResult;

import java.util.Collection;

/**
 * @author volodymyr.tsukur
 */
public interface BaseRepository<E extends IdentifiableAndVersioned> {

    E create(E entity);

    E update(E entity);

    E findById(Long id) throws EntityNotFoundException;

    Collection<E> findAll();

    PaginatedResult<E> findSeveral(Pagination pagination);

    void delete(Long id);

}
