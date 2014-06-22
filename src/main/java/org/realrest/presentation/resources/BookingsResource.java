package org.realrest.presentation.resources;

import org.realrest.domain.Booking;
import org.realrest.application.service.BookingService;
import org.realrest.presentation.transitions.CreateBookingTransition;

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
public class BookingsResource {

    @Inject
    private BookingService bookingService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final CreateBookingTransition data, @Context final UriInfo uriInfo) {
        final Booking result = bookingService.create(data);
        final URI bookingURI = BookingResource.readURI(uriInfo.getBaseUriBuilder(), result.getId());
        return Response.created(bookingURI).build();
    }

}
