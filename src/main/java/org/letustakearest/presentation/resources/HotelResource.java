package org.letustakearest.presentation.resources;

import com.google.code.siren4j.Siren4J;
import com.google.code.siren4j.component.Entity;
import org.letustakearest.application.service.HotelService;
import org.letustakearest.domain.EntityNotFoundException;
import org.letustakearest.domain.Hotel;
import org.letustakearest.presentation.representations.HotelWithPlacesRepresentationBuilder;
import org.letustakearest.presentation.representations.HotelRepresentationBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class HotelResource {

    private Long id;

    private HotelService hotelService;

    public HotelResource(final Long id, final HotelService hotelService) {
        this.id = id;
        this.hotelService = hotelService;
    }

    @GET
    @Produces({ Siren4J.JSON_MEDIATYPE })
    public Entity read(@Context final UriInfo uriInfo) {
        final Hotel hotel = findHotel();
        return new HotelRepresentationBuilder(hotel, uriInfo).build();
    }

    @GET
    @Produces({ "application/vnd.siren.hotel.v2+json" })
    public Entity readWithPlaces(@Context final UriInfo uriInfo) {
        return prepareHotelAsPlaceRepresentation(uriInfo);
    }

    @GET
    @Path("/as-place")
    @Produces({ Siren4J.JSON_MEDIATYPE })
    public Entity readWithPlacesViaURI(@Context final UriInfo uriInfo) {
        return prepareHotelAsPlaceRepresentation(uriInfo);
    }

    private Entity prepareHotelAsPlaceRepresentation(UriInfo uriInfo) {
        final Hotel hotel = findHotel();
        return new HotelWithPlacesRepresentationBuilder(hotel, uriInfo).build();
    }

    private Hotel findHotel() throws WebApplicationException {
        try {
            return hotelService.findById(id);
        }
        catch (final EntityNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

}
