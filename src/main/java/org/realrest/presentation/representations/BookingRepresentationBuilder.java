package org.realrest.presentation.representations;

import com.google.code.siren4j.component.Entity;
import com.google.code.siren4j.component.Link;
import com.google.code.siren4j.component.builder.EntityBuilder;
import com.google.code.siren4j.component.builder.LinkBuilder;
import org.realrest.domain.Booking;
import org.realrest.presentation.resources.BookingResource;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class BookingRepresentationBuilder {

    public Entity build(final Booking booking, final UriBuilder uriBuilder) {
        return EntityBuilder.newInstance().
                setComponentClass("booking").
                addProperty("id", booking.getId()).
                addProperty("from", booking.getFrom()).
                addProperty("to", booking.getTo()).
                addProperty("includeBreakfast", booking.isIncludeBreakfast()).
                addLink(
                        LinkBuilder.newInstance().
                                setHref(buildURI(booking, uriBuilder).toString()).
                                setRelationship(Link.RELATIONSHIP_SELF).
                                build()).
                build();
    }

    public URI buildURI(final Booking booking, final UriBuilder uriBuilder) {
        return uriBuilder.
                path(BookingResource.class).
                path(booking.getId().toString()).
                build();
    }

}
