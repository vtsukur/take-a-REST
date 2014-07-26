package org.letustakearest.presentation.representations.siren;

import org.letustakearest.domain.Booking;
import org.letustakearest.presentation.representations.BookingRepresentationAssembler;

import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class SirenBookingRepresentationAssembler extends BaseSirenRepresentationAssembler
        implements BookingRepresentationAssembler {

    public SirenBookingRepresentationAssembler(final UriInfo uriInfo) {
        super(uriInfo);
    }

    @Override
    public Object from(final Booking booking) {
        return new BookingRepresentationBuilder(booking, uriInfo).build();
    }

}
