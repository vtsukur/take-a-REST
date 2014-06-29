package org.realrest.presentation.resources;

import com.google.code.siren4j.Siren4J;
import com.google.code.siren4j.component.Entity;
import org.realrest.application.service.BookingService;
import org.realrest.domain.Booking;
import org.realrest.domain.EntityNotFoundException;
import org.realrest.presentation.representations.BookingRepresentationBuilder;
import org.realrest.presentation.transitions.PayForBookingTransition;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class BookingResource {

    @Inject
    private BookingService bookingService;

    @GET
    @Path("/{id}")
    @Produces({ Siren4J.JSON_MEDIATYPE, MediaType.APPLICATION_JSON })
    public Entity read(@PathParam("id") final Long id, @Context final UriInfo uriInfo) {
        try {
            final Booking booking = bookingService.findById(id);
            return new BookingRepresentationBuilder(booking, uriInfo).build();
        }
        catch (final EntityNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @POST
    @Path("/{id}/payment")
    @Produces({ Siren4J.JSON_MEDIATYPE, MediaType.APPLICATION_JSON })
    @Consumes(MediaType.APPLICATION_JSON)
    public Entity pay(@PathParam("id") final Long id, @Context final UriInfo uriInfo,
                      final PayForBookingTransition data) {
        try {
            final Booking booking = bookingService.pay(id, data);
            return new BookingRepresentationBuilder(booking, uriInfo).build();
        }
        catch (final EntityNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @DELETE
    @Path("/{id}")
    public Response cancel(@PathParam("id") final Long id) {
        bookingService.delete(id);
        return Response.noContent().build();
    }

}
