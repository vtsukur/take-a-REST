package org.letustakearest.presentation.representations.siren;

import com.google.code.siren4j.component.Entity;
import org.letustakearest.domain.Booking;

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
