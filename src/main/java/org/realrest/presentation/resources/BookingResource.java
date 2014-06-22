package org.realrest.presentation.resources;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.realrest.application.service.BookingService;
import org.realrest.domain.Booking;
import org.realrest.domain.BookingNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.lang.reflect.Method;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
@Path("/bookings/item")
public class BookingResource {

    @Inject
    private BookingService bookingService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Booking read(@PathParam("id") final Long id) {
        try {
            return bookingService.findById(id);
        }
        catch (final BookingNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    public static URI readURI(final UriBuilder uriBuilder, final Long id) {
        return uriBuilder.
                path(BookingResource.class).
                path(BookingResource.readMethod()).
                build(id);
    }

    private static Method readMethod() {
        return MethodUtils.getAccessibleMethod(BookingResource.class, "read", Long.class);
    }

}
