package org.letustakearest.domain.repository.impl;

import org.letustakearest.domain.Hotel;
import org.letustakearest.domain.repository.HotelRepository;

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
