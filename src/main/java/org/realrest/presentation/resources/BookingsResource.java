package org.realrest.presentation.resources;

import com.google.code.siren4j.Siren4J;
import com.google.code.siren4j.component.Entity;
import org.realrest.application.service.BookingService;
import org.realrest.domain.Booking;
import org.realrest.domain.EntityNotFoundException;
import org.realrest.presentation.representations.BookingRepresentationBuilder;
import org.realrest.presentation.representations.BookingsRepresentationBuilder;
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

    @GET
    @Produces({ Siren4J.JSON_MEDIATYPE, MediaType.APPLICATION_JSON })
    public Entity browse(@Context final UriInfo uriInfo) {
        return new BookingsRepresentationBuilder(bookingService.findAll(), uriInfo).build();
    }

    @POST
    @Produces({ Siren4J.JSON_MEDIATYPE, MediaType.APPLICATION_JSON })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final CreateBookingTransition data, @Context final UriInfo uriInfo) {
        final Booking result;
        try {
            result = bookingService.create(data);
        } catch (EntityNotFoundException e) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        final URI bookingURI = BookingRepresentationBuilder.selfURI(result, uriInfo);
        return Response.created(bookingURI).build();
    }

    @Path("/{id}")
    public BookingResource item(@PathParam("id") final Long id) {
        return new BookingResource(id, bookingService);
    }

}
