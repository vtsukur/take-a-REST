package org.takearest.presentation.representations;

import org.takearest.domain.Hotel;

/**
 * @author volodymyr.tsukur
 */
public interface HotelRepresentationAssembler {

    Object from(Hotel hotel);

}
