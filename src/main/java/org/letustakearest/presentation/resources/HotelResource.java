package org.letustakearest.presentation.resources;

import com.google.code.siren4j.Siren4J;
import com.google.code.siren4j.component.Entity;
import org.letustakearest.application.service.HotelService;
import org.letustakearest.domain.EntityNotFoundException;
import org.letustakearest.domain.Hotel;
import org.letustakearest.presentation.representations.HotelWithPlacesRepresentationBuilder;
import org.letustakearest.presentation.representations.HotelRepresentationBuilder;

import javax.ws.rs.GET;
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
        try {
            final Hotel hotel = hotelService.findById(id);
            return new HotelRepresentationBuilder(hotel, uriInfo).build();
        }
        catch (final EntityNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @GET
    @Produces({ "application/vnd.siren.hotel.v2+json" })
    public Entity readWithPlaces(@Context final UriInfo uriInfo) {
        try {
            final Hotel hotel = hotelService.findById(id);
            return new HotelWithPlacesRepresentationBuilder(hotel, uriInfo).build();
        }
        catch (final EntityNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

}
