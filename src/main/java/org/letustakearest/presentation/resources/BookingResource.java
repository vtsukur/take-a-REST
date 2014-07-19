package org.letustakearest.presentation.resources;

import com.google.code.siren4j.Siren4J;
import org.letustakearest.application.service.BookingService;
import org.letustakearest.domain.Booking;
import org.letustakearest.domain.EntityNotFoundException;
import org.letustakearest.presentation.cache.EntityTagFactory;
import org.letustakearest.presentation.representations.BookingRepresentationBuilder;
import org.letustakearest.presentation.transitions.PayForBookingTransition;
import org.letustakearest.presentation.transitions.UpdateBookingTransition;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

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
    @Produces({ Siren4J.JSON_MEDIATYPE })
    public Response read(
            @Context final UriInfo uriInfo,
            @Context final Request request) {
        try {
            final Booking booking = bookingService.findById(id);
            final Response.ResponseBuilder responseBuilder = request.evaluatePreconditions(entityTag(booking));

            if (responseBuilder == null) {
                return Response.ok(new BookingRepresentationBuilder(booking, uriInfo).build()).
                        tag(String.valueOf(booking.getVersion())).
                        build();
            }
            else {
                return responseBuilder.build();
            }
        }
        catch (final EntityNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ Siren4J.JSON_MEDIATYPE })
    public Response update(
            final UpdateBookingTransition transition,
            @Context final UriInfo uriInfo,
            @Context final Request request) {
        try {
            Booking booking = bookingService.findById(id);
            final Response.ResponseBuilder responseBuilder = request.evaluatePreconditions(entityTag(booking));

            if (responseBuilder == null) {
                booking = bookingService.update(booking, transition);
                return Response.ok(new BookingRepresentationBuilder(booking, uriInfo).build()).
                        tag(entityTag(booking)).
                        build();
            }
            else {
                return responseBuilder.build();
            }
        }
        catch (final EntityNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @POST
    @Path("/payment")
    @Produces({ Siren4J.JSON_MEDIATYPE })
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pay(
            final PayForBookingTransition transition,
            @Context final UriInfo uriInfo,
            @Context final Request request) {
        try {
            Booking booking = bookingService.findById(id);
            final Response.ResponseBuilder responseBuilder = request.evaluatePreconditions(entityTag(booking));

            if (responseBuilder == null) {
                booking = bookingService.pay(booking, transition);
                return Response.ok(new BookingRepresentationBuilder(booking, uriInfo).build()).
                        tag(entityTag(booking)).
                        build();
            }
            else {
                return responseBuilder.build();
            }
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

    private EntityTag entityTag(final Booking booking) {
        return EntityTagFactory.entityTag(booking.getVersion());
    }

}
