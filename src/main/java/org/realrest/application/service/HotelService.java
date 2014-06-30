package org.realrest.application.service;

import org.realrest.domain.EntityNotFoundException;
import org.realrest.domain.Hotel;
import org.realrest.domain.PaginatedResult;

/**
 * @author volodymyr.tsukur
 */
public interface HotelService {

    Hotel findById(Long id) throws EntityNotFoundException;

    PaginatedResult<Hotel> findSeveral(Pagination pagination);

}
