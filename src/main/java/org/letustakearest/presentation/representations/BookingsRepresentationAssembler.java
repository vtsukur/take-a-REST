package org.letustakearest.presentation.representations;

import org.letustakearest.domain.Booking;

import java.util.Collection;

/**
 * @author volodymyr.tsukur
 */
public interface BookingsRepresentationAssembler {

    Object from(Collection<Booking> bookings);

}
