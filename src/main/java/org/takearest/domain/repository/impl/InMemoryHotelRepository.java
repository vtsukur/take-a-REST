package org.takearest.domain.repository.impl;

import org.takearest.domain.Hotel;
import org.takearest.domain.repository.HotelRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class InMemoryHotelRepository extends BaseInMemoryRepository<Hotel> implements HotelRepository {

    public InMemoryHotelRepository() {
        super("Hotel");
    }

}
