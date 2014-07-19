package org.letustakearest.domain.repository.impl;

import org.letustakearest.domain.Place;
import org.letustakearest.domain.repository.PlaceRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class InMemoryPlaceRepository extends BaseInMemoryRepository<Place> implements PlaceRepository {

    public InMemoryPlaceRepository() {
        super("Place");
    }

}
