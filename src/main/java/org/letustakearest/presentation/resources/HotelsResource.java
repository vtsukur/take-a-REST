package org.letustakearest.presentation.resources;

import com.google.code.siren4j.Siren4J;
import org.letustakearest.application.service.HotelService;
import org.letustakearest.application.service.Pagination;
import org.letustakearest.presentation.representations.HotelRepresentationAssembler;
import org.letustakearest.presentation.representations.HotelsRepresentationAssembler;
import org.letustakearest.presentation.representations.cdi.SelectByAcceptHeader;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
@Path("/hotels")
public class HotelsResource {

    @Inject
    private HotelService hotelService;

    @Inject @SelectByAcceptHeader
    private HotelsRepresentationAssembler hotelsRepresentationAssembler;

    @Inject @SelectByAcceptHeader
    private HotelRepresentationAssembler hotelRepresentationAssembler;

    @GET
    @Produces({ Siren4J.JSON_MEDIATYPE })
    public Response browse(
            @QueryParam("offset") final Integer offset,
            @QueryParam("limit") final Integer limit) {
        final Pagination pagination = Pagination.getPagination(offset, limit);
        return Response.ok(
                hotelsRepresentationAssembler.from(hotelService.findSeveral(pagination))).
                build();
    }

    @Path("/{id}")
    public HotelResource item(@PathParam("id") final Long id) {
        return new HotelResource(id, hotelService, hotelRepresentationAssembler);
    }

    public static URI selfURI(final UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().
                path(HotelsResource.class).
                build();
    }

}
