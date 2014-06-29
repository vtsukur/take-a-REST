package org.realrest.presentation.resources;

import com.google.code.siren4j.component.Entity;
import org.realrest.application.service.BookingService;
import org.realrest.domain.Booking;
import org.realrest.domain.BookingNotFoundException;
import org.realrest.presentation.representations.BookingRepresentationBuilder;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * @author volodymyr.tsukur
 */
public class BookingResource {

    @Inject
    private BookingService bookingService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Entity read(@PathParam("id") final Long id, @Context final UriInfo uriInfo) {
        try {
            final Booking booking = bookingService.findById(id);
            return new BookingRepresentationBuilder(booking, uriInfo).build();
        }
        catch (final BookingNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

}
