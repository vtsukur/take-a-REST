package org.letustakearest.domain.repository;

import org.letustakearest.application.service.Pagination;
import org.letustakearest.domain.EntityNotFoundException;
import org.letustakearest.domain.IdentifiableAndVersioned;
import org.letustakearest.domain.PaginatedResult;

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
