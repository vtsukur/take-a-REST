package org.realrest.domain.repository.impl;

import org.realrest.application.service.Pagination;
import org.realrest.domain.EntityNotFoundException;
import org.realrest.domain.Identifiable;
import org.realrest.domain.repository.BaseRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author volodymyr.tsukur
 */
public abstract class BaseInMemoryRepository<E extends Identifiable> implements BaseRepository<E> {

    protected final Map<Long, E> store = Collections.synchronizedMap(new LinkedHashMap<>());

    private final AtomicLong incrementalId = new AtomicLong(0);

    private final String entityName;

    protected BaseInMemoryRepository(final String entityName) {
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
    public List<E> findSeveral(final Pagination pagination) {
        final int startIndex = startIndex(pagination.getOffset());
        final int endIndex = endIndex(startIndex, pagination.getLimit());
        return new ArrayList<>(store.values()).subList(startIndex, endIndex);
    }

    @Override
    public void delete(final Long id) {
        store.remove(id);
    }

    private int startIndex(final int offset) {
        if (offset < 0) {
            return 0;
        }
        else if (offset < totalCount()) {
            return offset;
        }
        else {
            return totalCount() - 1;
        }
    }

    private int endIndex(final int startIndex, final int limit) {
        final int optimisticEndIndex = startIndex + limit;
        if (optimisticEndIndex >= totalCount()) {
            return totalCount();
        }
        else {
            return optimisticEndIndex;
        }
    }

    @Override
    public int totalCount() {
        return store.size();
    }

}
