package org.letustakearest.presentation.representations.hal;

import com.theoryinpractise.halbuilder.api.Representation;
import org.letustakearest.domain.Booking;
import org.letustakearest.presentation.representations.BookingsRepresentationAssembler;
import org.letustakearest.presentation.resources._BookingResource;
import org.letustakearest.presentation.resources._BookingsResource;

import javax.ws.rs.core.UriInfo;
import java.util.Collection;

/**
 * @author volodymyr.tsukur
 */
public class HalBookingsRepresentationAssembler extends BaseHalRepresentationAssembler
        implements BookingsRepresentationAssembler {

    public HalBookingsRepresentationAssembler(final UriInfo uriInfo) {
        super(uriInfo);
    }

    @Override
    public Object from(final Collection<Booking> bookings) {
        return new Builder(bookings).prepare();
    }

    private class Builder {

        private Representation representation;

        private final Collection<Booking> bookings;

        Builder(final Collection<Booking> bookings) {
            representation = newRepresentation(_BookingsResource.selfURI(uriInfo));
            this.bookings = bookings;
        }

        Representation prepare() {
            addSubResources();
            return representation;
        }

        private void addSubResources() {
            bookings.stream().
                    map(this::toEmbeddedBookingRepresentation).
                    reduce(representation,
                            (accumulator, i) -> accumulator.withRepresentation(rel("booking"), i));
        }

        private Representation toEmbeddedBookingRepresentation(final Booking booking) {
            return newRepresentation(_BookingResource.selfURI(booking, uriInfo));
        }

    }

}
