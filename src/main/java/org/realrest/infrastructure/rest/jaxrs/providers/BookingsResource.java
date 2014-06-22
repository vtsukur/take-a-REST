package org.realrest.infrastructure.rest.jaxrs.providers;

import org.realrest.infrastructure.rest.jaxrs.transitions.CreateBookingTransition;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public Response createBooking(final CreateBookingTransition data, @Context final UriInfo uriInfo) {
        final URI bookingURI = uriInfo.getBaseUriBuilder().path("bookings").path("1").build();
        return Response.created(bookingURI).build();
    }

}
