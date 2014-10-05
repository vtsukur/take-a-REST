package org.letustakearest.presentation.representations.siren;

import com.google.code.siren4j.component.Link;
import com.google.code.siren4j.component.builder.EntityBuilder;
import com.google.code.siren4j.component.builder.LinkBuilder;
import org.letustakearest.domain.Booking;
import org.letustakearest.presentation.resources.BookingResource;
import org.letustakearest.presentation.resources.HotelResource;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
abstract class BaseBookingRepresentationBuilder {

    protected final Booking booking;

    protected final UriInfo uriInfo;

    public BaseBookingRepresentationBuilder(final Booking booking, final UriInfo uriInfo) {
        this.booking = booking;
        this.uriInfo = uriInfo;
    }

    protected EntityBuilder builder() {
        return EntityBuilder.newInstance().
                setComponentClass("booking").
                addProperty("roomType", booking.getPlace().getCategory().name()).
                addProperty("price", booking.getPrice()).
                addProperty("checkIn", booking.getCheckIn()).
                addProperty("checkOut", booking.getCheckOut()).
                addProperty("includeBreakfast", booking.isIncludeBreakfast()).
                addProperty("paid", booking.getPayment() != null).
                addLink(LinkBuilder.newInstance().
                        setHref(selfHref()).
                        setRelationship(Link.RELATIONSHIP_SELF).
                        build()).
                addLink(LinkBuilder.newInstance().
                        setHref(HotelResource.selfURI(booking.getPlace().getHotel(), uriInfo).toString()).
                        setRelationship("hotel").
                        build());
    }

    protected String selfHref() {
        return selfURI().toString();
    }

    private URI selfURI() {
        return BookingResource.selfURI(booking, uriInfo);
    }

}
