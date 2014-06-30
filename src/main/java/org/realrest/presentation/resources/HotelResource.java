package org.realrest.presentation.resources;

import com.google.code.siren4j.Siren4J;
import com.google.code.siren4j.component.Entity;
import org.realrest.application.service.HotelService;
import org.realrest.domain.EntityNotFoundException;
import org.realrest.domain.Hotel;
import org.realrest.presentation.representations.HotelRepresentationBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
    @Produces({ Siren4J.JSON_MEDIATYPE, MediaType.APPLICATION_JSON })
    public Entity read(@Context final UriInfo uriInfo) {
        try {
            final Hotel hotel = hotelService.findById(id);
            return new HotelRepresentationBuilder(hotel, uriInfo).build();
        }
        catch (final EntityNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

}
