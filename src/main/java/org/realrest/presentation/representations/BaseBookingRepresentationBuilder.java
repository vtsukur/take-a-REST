package org.realrest.presentation.representations;

import com.google.code.siren4j.component.Link;
import com.google.code.siren4j.component.builder.EntityBuilder;
import com.google.code.siren4j.component.builder.LinkBuilder;
import org.realrest.domain.Booking;
import org.realrest.presentation.resources.BookingsResource;

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
                addProperty("from", booking.getFrom()).
                addProperty("to", booking.getTo()).
                addProperty("includeBreakfast", booking.isIncludeBreakfast()).
                addLink(LinkBuilder.newInstance().
                        setHref(selfHref()).
                        setRelationship(Link.RELATIONSHIP_SELF).
                        build());
    }

    protected String selfHref() {
        return selfURI().toString();
    }

    private URI selfURI() {
        return selfURI(booking, uriInfo);
    }

    public static URI selfURI(final Booking booking, final UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().
                path(BookingsResource.class).
                path(booking.getId().toString()).
                build();
    }

}
