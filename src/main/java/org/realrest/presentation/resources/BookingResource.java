package org.realrest.presentation.resources;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.realrest.application.service.BookingService;
import org.realrest.domain.Booking;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
        return bookingService.findById(id);
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
