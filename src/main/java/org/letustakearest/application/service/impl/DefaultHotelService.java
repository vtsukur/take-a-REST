package org.letustakearest.application.service.impl;

import org.letustakearest.application.service.HotelService;
import org.letustakearest.application.service.Pagination;
import org.letustakearest.domain.EntityNotFoundException;
import org.letustakearest.domain.Hotel;
import org.letustakearest.domain.PaginatedResult;
import org.letustakearest.domain.repository.HotelRepository;

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
