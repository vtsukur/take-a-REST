package org.realrest.domain.repository.impl;

import org.realrest.domain.Hotel;
import org.realrest.domain.repository.HotelRepository;

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
