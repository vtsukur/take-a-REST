package org.takearest.presentation.representations;

import org.takearest.domain.Hotel;
import org.takearest.domain.PaginatedResult;

/**
 * @author volodymyr.tsukur
 */
public interface HotelsRepresentationAssembler {

    Object from(PaginatedResult<Hotel> hotels);

}
