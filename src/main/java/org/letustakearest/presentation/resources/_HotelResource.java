package org.letustakearest.presentation.resources;

import com.google.code.siren4j.Siren4J;
import com.google.code.siren4j.component.Entity;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import org.letustakearest.application.service.HotelService;
import org.letustakearest.domain.EntityNotFoundException;
import org.letustakearest.domain.Hotel;
import org.letustakearest.presentation.representations.HotelRepresentationAssembler;
import org.letustakearest.presentation.representations.siren.HotelWithPlacesRepresentationBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

/**
 * @author volodymyr.tsukur
 */
public class _HotelResource {

    private Long id;

    private HotelService hotelService;

    private HotelRepresentationAssembler hotelRepresentationAssembler;

    public _HotelResource(final Long id, final HotelService hotelService,
                          final HotelRepresentationAssembler hotelRepresentationAssembler) {
        this.id = id;
        this.hotelService = hotelService;
        this.hotelRepresentationAssembler = hotelRepresentationAssembler;
    }

    @GET
    @Produces({ RepresentationFactory.HAL_JSON, Siren4J.JSON_MEDIATYPE })
    public Response read() {
        final Hotel hotel = findHotel();
        return Response.ok(hotelRepresentationAssembler.from(hotel)).build();
    }

    @GET
    @Produces({ "application/vnd.siren.hotel.v2+json" })
    public Response readWithPlaces(@Context final UriInfo uriInfo) {
        return Response.ok(prepareHotelAsPlaceRepresentation(uriInfo)).build();
    }

    @GET
    @Path("/runtime-content-negotiation")
    public Response readViaRuntimeContentNegotiation(
            @Context final UriInfo uriInfo,
            @Context final Request request) {
        final List<Variant> variants = Variant.mediaTypes(
                MediaType.valueOf(Siren4J.JSON_MEDIATYPE),
                MediaType.valueOf("application/vnd.siren.hotel.v2+json")
        ).build();
        final Variant variant = request.selectVariant(variants);
        if (variant == null) {
            return Response.notAcceptable(variants).build();
        }
        else {
            final Hotel hotel = findHotel();
            if (variant.getMediaType().equals(MediaType.valueOf("application/vnd.siren.hotel.v2+json"))) {
                return Response.ok(prepareHotelAsPlaceRepresentation(uriInfo), variant).build();
            }
            else {
                return Response.ok(hotelRepresentationAssembler.from(hotel), variant).build();
            }
        }
    }

    @GET
    @Produces({ Siren4J.JSON_MEDIATYPE })
    @Path("/versioning-by-header")
    public Response readViaRuntimeContentNegotiation(
            @Context final UriInfo uriInfo,
            @Context final HttpHeaders httpHeaders) {
        final Hotel hotel = findHotel();
        if (httpHeaders.getRequestHeader("X-Version").contains("2")) {
            return Response.ok(prepareHotelAsPlaceRepresentation(uriInfo)).build();
        }
        else {
            return Response.ok(hotelRepresentationAssembler.from(hotel)).build();
        }
    }

    @GET
    @Path("/as-place")
    @Produces({ Siren4J.JSON_MEDIATYPE })
    public Response readWithPlacesViaURI(@Context final UriInfo uriInfo) {
        return Response.ok(prepareHotelAsPlaceRepresentation(uriInfo)).build();
    }

    private Entity prepareHotelAsPlaceRepresentation(final UriInfo uriInfo) {
        final Hotel hotel = findHotel();
        return new HotelWithPlacesRepresentationBuilder(hotel, uriInfo).build();
    }

    private Hotel findHotel() throws WebApplicationException {
        try {
            return hotelService.findById(id);
        }
        catch (final EntityNotFoundException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    public static URI selfURI(final Hotel hotel, final UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().
                path(_HotelsResource.class).
                path(hotel.getId().toString()).
                build();
    }

}
