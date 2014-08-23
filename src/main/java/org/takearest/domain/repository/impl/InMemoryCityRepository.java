package org.takearest.domain.repository.impl;

import org.takearest.domain.City;
import org.takearest.domain.repository.CityRepository;

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
