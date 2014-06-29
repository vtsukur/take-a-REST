package org.realrest.domain.repository.impl;

import org.realrest.domain.City;
import org.realrest.domain.repository.CityRepository;

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
