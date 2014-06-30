package org.realrest.application.service;

import org.realrest.domain.EntityNotFoundException;
import org.realrest.domain.Hotel;

import java.util.Collection;
import java.util.List;

/**
 * @author volodymyr.tsukur
 */
public interface HotelService {

    Hotel findById(Long id) throws EntityNotFoundException;

    Collection<Hotel> findAll();

    List<Hotel> findSeveral(Pagination pagination);

}
