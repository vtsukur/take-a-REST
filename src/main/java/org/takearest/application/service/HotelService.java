package org.takearest.application.service;

import org.takearest.domain.EntityNotFoundException;
import org.takearest.domain.Hotel;
import org.takearest.domain.PaginatedResult;

/**
 * @author volodymyr.tsukur
 */
public interface HotelService {

    Hotel findById(Long id) throws EntityNotFoundException;

    PaginatedResult<Hotel> findSeveral(Pagination pagination);

}
