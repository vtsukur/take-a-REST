package org.realrest.presentation.representations;

import com.google.code.siren4j.component.Entity;
import org.realrest.domain.Hotel;

import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class HotelRepresentationBuilder extends BaseHotelRepresentationBuilder {

    public HotelRepresentationBuilder(final Hotel hotel, final UriInfo uriInfo) {
        super(hotel, uriInfo);
    }

    public Entity build() {
        return builder().
                build();
    }

}
