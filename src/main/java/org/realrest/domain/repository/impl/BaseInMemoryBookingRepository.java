package org.realrest.domain.repository.impl;

import org.realrest.domain.EntityNotFoundException;
import org.realrest.domain.Identifiable;
import org.realrest.domain.repository.BaseRepository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author volodymyr.tsukur
 */
public abstract class BaseInMemoryBookingRepository<E extends Identifiable> implements BaseRepository<E> {

    protected final Map<Long, E> store = new ConcurrentHashMap<>();

    private final AtomicLong incrementalId = new AtomicLong(0);

    private final String entityName;

    protected BaseInMemoryBookingRepository(final String entityName) {
        this.entityName = entityName;
    }

    @Override
    public E create(final E data) {
        final Long id = incrementalId.incrementAndGet();
        data.setId(id);
        store.put(id, data);
        return data;
    }

    @Override
    public E findById(final Long id) throws EntityNotFoundException {
        final E result = store.getOrDefault(id, null);
        if (result != null) {
            return result;
        }
        else {
            throw new EntityNotFoundException(String.format("%s with id %d not found", entityName, id));
        }
    }

    @Override
    public Collection<E> findAll() {
        return store.values();
    }

    @Override
    public void delete(final Long id) {
        store.remove(id);
    }

}
