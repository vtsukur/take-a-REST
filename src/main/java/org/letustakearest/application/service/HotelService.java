package org.letustakearest.application.service;

import org.letustakearest.domain.EntityNotFoundException;
import org.letustakearest.domain.Hotel;
import org.letustakearest.domain.PaginatedResult;

/**
 * @author volodymyr.tsukur
 */
public interface HotelService {

    Hotel findById(Long id) throws EntityNotFoundException;

    PaginatedResult<Hotel> findSeveral(Pagination pagination);

}
