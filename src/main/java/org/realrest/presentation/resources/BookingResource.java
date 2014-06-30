package org.realrest.presentation.resources;

import com.google.code.siren4j.Siren4J;
import com.google.code.siren4j.component.Entity;
import org.realrest.application.service.BookingService;
import org.realrest.domain.Booking;
import org.realrest.domain.EntityNotFoundException;
import org.realrest.presentation.representations.BookingRepresentationBuilder;
import org.realrest.presentation.transitions.PayForBookingTransition;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class BookingResource {

    private Long id;

    private BookingService bookingService;

    public BookingResource(final Long id, final BookingService bookingService) {
        this.id = id;
        this.bookingService = bookingService;
    }

    @GET
    @Produces({ Siren4J.JSON_MEDIATYPE, MediaType.APPLICATION_JSON })
    public Entity read(@Context final UriInfo uriInfo) {
        try {
            final Booking booking = bookingService.findById(id);
            return new BookingRepresentationBuilder(booking, uriInfo).build();
        }
        catch (final EntityNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @POST
    @Path("/payment")
    @Produces({ Siren4J.JSON_MEDIATYPE, MediaType.APPLICATION_JSON })
    @Consumes(MediaType.APPLICATION_JSON)
    public Entity pay(@Context final UriInfo uriInfo, final PayForBookingTransition data) {
        try {
            final Booking booking = bookingService.pay(id, data);
            return new BookingRepresentationBuilder(booking, uriInfo).build();
        }
        catch (final EntityNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @DELETE
    public Response cancel() {
        bookingService.delete(id);
        return Response.noContent().build();
    }

}
