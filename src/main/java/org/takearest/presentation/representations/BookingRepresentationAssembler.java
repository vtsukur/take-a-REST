package org.takearest.presentation.representations;

import org.takearest.domain.Booking;

/**
 * @author volodymyr.tsukur
 */
public interface BookingRepresentationAssembler {

    Object from(Booking booking);

}
