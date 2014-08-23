package org.takearest.presentation.resources;

import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import org.takearest.application.service.HotelService;
import org.takearest.application.service.Pagination;
import org.takearest.presentation.representations.HotelRepresentationAssembler;
import org.takearest.presentation.representations.HotelsRepresentationAssembler;
import org.takearest.presentation.representations.cdi.SelectByAcceptHeader;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
@Path("/hotels")
public class _HotelsResource {

    @Inject
    private HotelService hotelService;

    @Inject @SelectByAcceptHeader
    private HotelsRepresentationAssembler hotelsRepresentationAssembler;

    @Inject @SelectByAcceptHeader
    private HotelRepresentationAssembler hotelRepresentationAssembler;

    @GET
    @Produces({ RepresentationFactory.HAL_JSON })
    public Response browse(
            @QueryParam("offset") final Integer offset,
            @QueryParam("limit") final Integer limit) {
        final Pagination pagination = Pagination.getPagination(offset, limit);
        return Response.ok(
                hotelsRepresentationAssembler.from(hotelService.findSeveral(pagination))).
                build();
    }

    @Path("/{id}")
    public _HotelResource item(@PathParam("id") final Long id) {
        return new _HotelResource(id, hotelService, hotelRepresentationAssembler);
    }

    public static URI selfURI(final UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().
                path(_HotelsResource.class).
                build();
    }

}
