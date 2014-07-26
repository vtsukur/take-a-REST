package org.letustakearest.presentation.representations;

import org.letustakearest.domain.Hotel;
import org.letustakearest.domain.PaginatedResult;

/**
 * @author volodymyr.tsukur
 */
public interface HotelsRepresentationAssembler {

    Object from(PaginatedResult<Hotel> hotels);

}
