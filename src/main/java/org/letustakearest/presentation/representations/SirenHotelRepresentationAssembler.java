package org.letustakearest.presentation.representations;

import org.letustakearest.domain.Hotel;

import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class SirenHotelRepresentationAssembler implements HotelRepresentationAssembler {

    private UriInfo uriInfo;

    public SirenHotelRepresentationAssembler(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    @Override
    public Object from(Hotel hotel) {
        return new HotelRepresentationBuilder(hotel, uriInfo).build();
    }

}
