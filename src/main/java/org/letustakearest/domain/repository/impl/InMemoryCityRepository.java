package org.letustakearest.domain.repository.impl;

import org.letustakearest.domain.City;
import org.letustakearest.domain.repository.CityRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class InMemoryCityRepository extends BaseInMemoryRepository<City> implements CityRepository {

    public InMemoryCityRepository() {
        super("City");
    }

}
