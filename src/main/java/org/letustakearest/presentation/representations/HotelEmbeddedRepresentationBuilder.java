package org.letustakearest.presentation.representations;

import com.google.code.siren4j.component.Entity;
import org.letustakearest.domain.Hotel;

import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class HotelEmbeddedRepresentationBuilder extends BaseHotelRepresentationBuilder {

    public HotelEmbeddedRepresentationBuilder(final Hotel hotel, final UriInfo uriInfo) {
        super(hotel, uriInfo);
    }

    public Entity build() {
        return builder().
                setRelationship("hotel").
                build();
    }

}
