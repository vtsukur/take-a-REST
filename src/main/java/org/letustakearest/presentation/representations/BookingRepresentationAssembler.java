package org.letustakearest.presentation.representations;

import org.letustakearest.domain.Booking;

/**
 * @author volodymyr.tsukur
 */
public interface BookingRepresentationAssembler {

    Object from(Booking booking);

}
