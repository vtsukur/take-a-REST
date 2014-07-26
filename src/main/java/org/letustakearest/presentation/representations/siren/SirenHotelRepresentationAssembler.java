package org.letustakearest.presentation.representations.siren;

import org.letustakearest.domain.Hotel;
import org.letustakearest.presentation.representations.HotelRepresentationAssembler;

import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class SirenHotelRepresentationAssembler extends BaseSirenRepresentationAssembler
        implements HotelRepresentationAssembler {

    public SirenHotelRepresentationAssembler(final UriInfo uriInfo) {
        super(uriInfo);
    }

    @Override
    public Object from(Hotel hotel) {
        return new HotelRepresentationBuilder(hotel, uriInfo).build();
    }

}
