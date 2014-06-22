package org.realrest.infrastructure.rest.jaxrs.providers;

import org.realrest.domain.Booking;
import org.realrest.domain.service.BookingService;
import org.realrest.infrastructure.rest.jaxrs.transitions.CreateBookingTransition;

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
@Path("bookings")
public class BookingsResource {

    @Inject
    private BookingService bookingService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBooking(final CreateBookingTransition data, @Context final UriInfo uriInfo) {
        final Booking result = bookingService.create(data);
        final URI bookingURI = uriInfo.getBaseUriBuilder().path("bookings").path(result.getId().toString()).build();
        return Response.created(bookingURI).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Booking readBooking(@PathParam("id") final Long id) {
        return bookingService.findById(id);
    }

}
