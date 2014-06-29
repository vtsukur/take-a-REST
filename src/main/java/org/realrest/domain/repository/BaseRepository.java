package org.realrest.domain.repository;

import org.realrest.domain.EntityNotFoundException;
import org.realrest.domain.Identifiable;

/**
 * @author volodymyr.tsukur
 */
public interface BaseRepository<E extends Identifiable> {

    E create(E data);

    E findById(Long id) throws EntityNotFoundException;

    void delete(Long id);

}
