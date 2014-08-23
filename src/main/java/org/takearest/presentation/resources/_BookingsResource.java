package org.takearest.presentation.resources;

import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import org.takearest.application.service.BookingService;
import org.takearest.domain.Booking;
import org.takearest.domain.EntityNotFoundException;
import org.takearest.presentation.cache.CacheControlFactory;
import org.takearest.presentation.representations.BookingRepresentationAssembler;
import org.takearest.presentation.representations.BookingsRepresentationAssembler;
import org.takearest.presentation.representations.cdi.SelectByAcceptHeader;
import org.takearest.presentation.transitions.CreateBookingTransition;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
* @author volodymyr.tsukur
*/
@Path("/bookings")
public class _BookingsResource {

    @Inject
    private BookingService bookingService;

    @Inject
    @SelectByAcceptHeader
    private BookingsRepresentationAssembler bookingsRepresentationAssembler;

    @Inject
    @SelectByAcceptHeader
    private BookingRepresentationAssembler bookingRepresentationAssembler;

    @GET
    @Produces({ RepresentationFactory.HAL_JSON })
    public Response browse() {
        return Response.
                ok(bookingsRepresentationAssembler.from(bookingService.findAll())).
                cacheControl(CacheControlFactory.oneMinute()).
                build();
    }

    @POST
    @Produces({ RepresentationFactory.HAL_JSON })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final CreateBookingTransition transition, @Context final UriInfo uriInfo) {
        final Booking result;
        try {
            result = bookingService.create(transition);
        } catch (EntityNotFoundException e) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        final URI bookingURI = _BookingResource.selfURI(result, uriInfo);
        return Response.created(bookingURI).build();
    }

//    @POST
//    @Produces({ Siren4J.JSON_MEDIATYPE })
//    @Consumes("application/vnd.booking.v2+json")
//    public Response create(final CreateBookingAsPlaceTransition transition, @Context final UriInfo uriInfo) {
//        final Booking result;
//        try {
//            result = bookingService.create(transition);
//        } catch (EntityNotFoundException e) {
//            throw new WebApplicationException(Response.Status.BAD_REQUEST);
//        }
//        final URI bookingURI = _BookingResource.selfURI(result, uriInfo);
//        return Response.created(bookingURI).build();
//    }

    @Path("/{id}")
    public _BookingResource item(@PathParam("id") final Long id) {
        return new _BookingResource(id, bookingService, bookingRepresentationAssembler);
    }

    public static URI selfURI(final UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().
                path(_BookingsResource.class).
                build();
    }

}
