package org.takearest.application.service.impl;

import org.takearest.application.service.HotelService;
import org.takearest.application.service.Pagination;
import org.takearest.domain.EntityNotFoundException;
import org.takearest.domain.Hotel;
import org.takearest.domain.PaginatedResult;
import org.takearest.domain.repository.HotelRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class DefaultHotelService implements HotelService {

    @Inject
    private HotelRepository hotelRepository;

    @Override
    public Hotel findById(final Long id) throws EntityNotFoundException {
        return hotelRepository.findById(id);
    }

    @Override
    public PaginatedResult<Hotel> findSeveral(final Pagination pagination) {
        return hotelRepository.findSeveral(pagination);
    }

}
