package org.takearest.presentation.representations.hal;

import com.theoryinpractise.halbuilder.api.Representation;
import org.takearest.domain.Booking;
import org.takearest.presentation.representations.BookingRepresentationAssembler;
import org.takearest.presentation.resources._BookingResource;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
public class HalBookingRepresentationAssembler extends BaseHalRepresentationAssembler
        implements BookingRepresentationAssembler {

    public HalBookingRepresentationAssembler(final UriInfo uriInfo) {
        super(uriInfo);
    }

    @Override
    public Object from(final Booking booking) {
        return new Builder(booking).prepare();
    }

    private class Builder {

        private Representation representation;

        private final Booking booking;

        Builder(final Booking booking) {
            representation = newRepresentation(selfURI(booking));
            this.booking = booking;
        }

        Representation prepare() {
            setProperties();
            addUpdateLinkIfAvailable();
            addPaymentLinkIfAvailable();
            addCancellationLinkIfAvailable();
            return representation;
        }

        private void setProperties() {
            representation = representation.
                    withProperty("from", booking.getFrom()).
                    withProperty("to", booking.getTo()).
                    withProperty("includeBreakfast", booking.isIncludeBreakfast());
        }

        private void addUpdateLinkIfAvailable() {
            if (booking.getState() == Booking.State.CREATED) {
                representation = representation.withLink(
                        rel("booking-update"), selfURI(booking));
            }
        }

        private void addPaymentLinkIfAvailable() {
            if (booking.getState() == Booking.State.CREATED) {
                representation = representation.withLink(
                        rel("booking-payment"), selfURI(booking) + "/payment");
            }
        }

        private void addCancellationLinkIfAvailable() {
            if (booking.getState() == Booking.State.CREATED ||
                    booking.getState() == Booking.State.PAID) {
                representation = representation.withLink(
                        rel("booking-cancellation"), selfURI(booking));
            }
        }

        private URI selfURI(final Booking booking) {
            return _BookingResource.selfURI(booking, uriInfo);
        }

    }

}
