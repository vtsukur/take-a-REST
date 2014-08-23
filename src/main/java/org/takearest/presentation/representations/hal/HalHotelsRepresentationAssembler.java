package org.takearest.presentation.representations.hal;

import com.theoryinpractise.halbuilder.api.Representation;
import org.takearest.application.service.Pagination;
import org.takearest.domain.Hotel;
import org.takearest.domain.PaginatedResult;
import org.takearest.presentation.representations.HotelsRepresentationAssembler;
import org.takearest.presentation.resources._HotelResource;
import org.takearest.presentation.resources._HotelsResource;

import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * @author volodymyr.tsukur
 */
public class HalHotelsRepresentationAssembler extends BaseHalRepresentationAssembler
        implements HotelsRepresentationAssembler {

    public HalHotelsRepresentationAssembler(final UriInfo uriInfo) {
        super(uriInfo);
    }

    @Override
    public Object from(final PaginatedResult<Hotel> hotels) {
        return addNextLinkIfRequired(
                addPrevLinkIfRequired(
                        addSubResources(
                                newRepresentation(hrefWithOffset(hotels.getActualPagination().getOffset())).
                                        withProperty("offset", hotels.getActualPagination().getOffset()).
                                        withProperty("limit", hotels.getActualPagination().getLimit()).
                                        withProperty("total", hotels.getTotal()),
                                hotels.getList()),
                        hotels),
                hotels);
    }

    private Representation addPrevLinkIfRequired(final Representation representation,
            final PaginatedResult<Hotel> hotels) {
        if (hotels.getActualPagination().getOffset() > 0) {
            final int prevOffset = Math.max(hotels.getActualPagination().getOffset() - Pagination.DEFAULT.getLimit(), 0);
            return representation.withLink("previous", hrefWithOffset(prevOffset));
        }
        else {
            return representation;
        }
    }

    private Representation addNextLinkIfRequired(final Representation representation,
            final PaginatedResult<Hotel> hotels) {
        final int nextOffset = hotels.getActualPagination().getOffset() + Pagination.DEFAULT.getLimit();
        if (nextOffset < hotels.getTotal()) {
            return representation.withLink("next", hrefWithOffset(nextOffset));
        }
        else {
            return representation;
        }
    }

    private Representation addSubResources(final Representation representation, final List<Hotel> hotels) {
        return hotels.stream().
                map(this::newEmbeddedHotelRepresentation).
                reduce(
                        representation,
                        (accumulator, i) -> accumulator.withRepresentation(rel("hotel"), i));
    }

    private Representation newEmbeddedHotelRepresentation(final Hotel hotel) {
        return newRepresentation(_HotelResource.selfURI(hotel, uriInfo)).
                withProperty("name", hotel.getName());
    }

    private String hrefWithOffset(final int offset) {
        if (offset != 0) {
            return String.format("%s?offset=%d", selfHref(), offset);
        }
        else {
            return selfHref();
        }
    }

    private String selfHref() {
        return _HotelsResource.selfURI(uriInfo).toString();
    }

}
