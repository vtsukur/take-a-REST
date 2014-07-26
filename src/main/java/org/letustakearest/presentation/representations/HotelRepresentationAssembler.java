package org.letustakearest.presentation.representations;

import org.letustakearest.domain.Hotel;

/**
 * @author volodymyr.tsukur
 */
public interface HotelRepresentationAssembler {

    Object from(Hotel hotel);

}
