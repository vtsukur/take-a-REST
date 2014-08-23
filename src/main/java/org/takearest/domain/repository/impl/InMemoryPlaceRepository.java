package org.takearest.domain.repository.impl;

import org.takearest.domain.Place;
import org.takearest.domain.repository.PlaceRepository;

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
