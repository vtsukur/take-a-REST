package org.realrest.presentation.resources;

import com.google.code.siren4j.Siren4J;
import com.google.code.siren4j.component.Entity;
import org.realrest.application.service.HotelService;
import org.realrest.application.service.Pagination;
import org.realrest.presentation.representations.HotelsRepresentationBuilder;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
@Path("/hotels")
public class HotelsResource {

    @Inject
    private HotelService hotelService;

    @GET
    @Produces({ Siren4J.JSON_MEDIATYPE, MediaType.APPLICATION_JSON })
    public Entity all(@Context final UriInfo uriInfo,
                      @QueryParam("offset") final Integer offset,
                      @QueryParam("limit") final Integer limit) {
        final Pagination pagination = Pagination.getPagination(offset, limit);
        return new HotelsRepresentationBuilder(
                hotelService.findSeveral(pagination),
                uriInfo).build();
    }

    @Path("/{id}")
    public HotelResource item(@PathParam("id") final Long id) {
        return new HotelResource(id, hotelService);
    }

}
