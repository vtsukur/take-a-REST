package org.realrest.presentation.resources;

import com.google.code.siren4j.component.Entity;
import org.realrest.application.service.BookingService;
import org.realrest.domain.BookingNotFoundException;
import org.realrest.presentation.representations.BookingRepresentationBuilder;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
@Path("/bookings/item")
public class BookingResource {

    @Inject
    private BookingService bookingService;

    @Inject
    private BookingRepresentationBuilder bookingRepresentationBuilder;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Entity read(@PathParam("id") final Long id, @Context final UriInfo uriInfo) {
        try {
            return bookingRepresentationBuilder.prepare(bookingService.findById(id));
        }
        catch (final BookingNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    public static URI readURI(final UriBuilder uriBuilder, final Long id) {
        return uriBuilder.
                path(BookingResource.class).
                path(id.toString()).
                build();
    }

}
