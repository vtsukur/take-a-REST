package org.realrest.application.service.impl;

import org.realrest.application.service.HotelService;
import org.realrest.domain.EntityNotFoundException;
import org.realrest.domain.Hotel;
import org.realrest.domain.repository.HotelRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;

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
    public Collection<Hotel> findAll() {
        return hotelRepository.findAll();
    }

}
