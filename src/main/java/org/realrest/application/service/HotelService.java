package org.realrest.application.service;

import org.realrest.domain.EntityNotFoundException;
import org.realrest.domain.Hotel;

import java.util.Collection;

/**
 * @author volodymyr.tsukur
 */
public interface HotelService {

    Hotel findById(Long id) throws EntityNotFoundException;

    Collection<Hotel> findAll();

}
