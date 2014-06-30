package org.realrest.presentation.representations;

import com.google.code.siren4j.component.Entity;
import org.realrest.domain.Booking;

import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class BookingEmbeddedRepresentationBuilder extends BaseBookingRepresentationBuilder {

    public BookingEmbeddedRepresentationBuilder(final Booking booking, final UriInfo uriInfo) {
        super(booking, uriInfo);
    }

    public Entity build() {
        return builder().setRelationship("booking").build();
    }

}
