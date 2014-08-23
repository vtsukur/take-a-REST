package org.takearest.presentation.representations;

import org.takearest.domain.Booking;

import java.util.Collection;

/**
 * @author volodymyr.tsukur
 */
public interface BookingsRepresentationAssembler {

    Object from(Collection<Booking> bookings);

}
