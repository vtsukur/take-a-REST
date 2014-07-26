package org.letustakearest.presentation.representations.siren;

import org.letustakearest.domain.Booking;
import org.letustakearest.presentation.representations.BookingsRepresentationAssembler;

import javax.ws.rs.core.UriInfo;
import java.util.Collection;

/**
 * @author volodymyr.tsukur
 */
public class SirenBookingsRepresentationAssembler extends BaseSirenRepresentationAssembler
        implements BookingsRepresentationAssembler {

    public SirenBookingsRepresentationAssembler(final UriInfo uriInfo) {
        super(uriInfo);
    }

    @Override
    public Object from(final Collection<Booking> bookings) {
        return new BookingsRepresentationBuilder(bookings, uriInfo).build();
    }

}
