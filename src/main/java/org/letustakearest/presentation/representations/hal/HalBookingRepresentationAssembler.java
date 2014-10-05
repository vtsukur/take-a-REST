package org.letustakearest.presentation.representations.hal;

import com.theoryinpractise.halbuilder.api.Representation;
import org.letustakearest.domain.Booking;
import org.letustakearest.presentation.representations.BookingRepresentationAssembler;
import org.letustakearest.presentation.resources.BookingResource;
import org.letustakearest.presentation.resources.HotelResource;

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
            addHotelLink();
            return representation;
        }

        private void addHotelLink() {
            representation = representation.withLink(
                    rel("hotel"), HotelResource.selfURI(booking.getPlace().getHotel(), uriInfo)
            );
        }

        private void setProperties() {
            representation = representation.
                    withProperty("hotel", booking.getPlace().getHotel().getName()).
                    withProperty("city", booking.getPlace().getHotel().getCity().getName()).
                    withProperty("roomType", booking.getPlace().getType().name()).
                    withProperty("price", booking.getPrice()).
                    withProperty("checkIn", booking.getCheckIn()).
                    withProperty("checkOut", booking.getCheckOut()).
                    withProperty("includeBreakfast", booking.isIncludeBreakfast()).
                    withProperty("paid", booking.getPayment() != null);
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
            return BookingResource.selfURI(booking, uriInfo);
        }

    }

}
