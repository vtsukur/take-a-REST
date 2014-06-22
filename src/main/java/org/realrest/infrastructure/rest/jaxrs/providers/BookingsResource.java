package org.realrest.infrastructure.rest.jaxrs.providers;

import org.realrest.domain.Booking;
import org.realrest.infrastructure.rest.jaxrs.transitions.CreateBookingTransition;

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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBooking(final CreateBookingTransition data, @Context final UriInfo uriInfo) {
        final URI bookingURI = uriInfo.getBaseUriBuilder().path("bookings").path("1").build();
        return Response.created(bookingURI).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Booking readBooking(@PathParam("id") final Long id) {
        final Booking booking = new Booking();
        booking.setId(id);
        return booking;
    }

}
