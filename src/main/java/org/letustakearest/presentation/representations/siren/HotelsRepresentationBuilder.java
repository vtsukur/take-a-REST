package org.letustakearest.presentation.representations.siren;

import com.google.code.siren4j.component.Entity;
import com.google.code.siren4j.component.Link;
import com.google.code.siren4j.component.builder.EntityBuilder;
import com.google.code.siren4j.component.builder.LinkBuilder;
import org.letustakearest.application.service.Pagination;
import org.letustakearest.domain.Hotel;
import org.letustakearest.domain.PaginatedResult;
import org.letustakearest.presentation.resources._HotelsResource;

import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author volodymyr.tsukur
 */
class HotelsRepresentationBuilder {

    private final PaginatedResult<Hotel> hotels;

    private final UriInfo uriInfo;

    public HotelsRepresentationBuilder(final PaginatedResult<Hotel> hotels,
                                       final UriInfo uriInfo) {
        this.hotels = hotels;
        this.uriInfo = uriInfo;
    }

    public Entity build() {
        EntityBuilder builder = EntityBuilder.newInstance().
                setComponentClass("hotels").
                addProperty("offset", hotels.getActualPagination().getOffset()).
                addProperty("limit", hotels.getActualPagination().getLimit()).
                addProperty("total", hotels.getTotal()).
                addSubEntities(hotels()).
                addLink(LinkBuilder.newInstance().
                        setHref(selfHrefWithOffset()).
                        setRelationship(Link.RELATIONSHIP_SELF).
                        build());
        return addNextLinkIfRequired(addPrevLinkIfRequired(builder)).build();
    }

    private List<Entity> hotels() {
        return hotels.getList().stream().
                map(hotel -> new HotelEmbeddedRepresentationBuilder(hotel, uriInfo).build()).
                collect(Collectors.toList());
    }

    private EntityBuilder addNextLinkIfRequired(final EntityBuilder builder) {
        final int nextOffset = hotels.getActualPagination().getOffset() + Pagination.DEFAULT.getLimit();
        if (nextOffset < hotels.getTotal()) {
            return builder.addLink(LinkBuilder.newInstance().
                    setHref(hrefWithOffset(nextOffset)).
                    setComponentClass("hotels").
                    setRelationship(Link.RELATIONSHIP_NEXT).
                    build());
        }
        else {
            return builder;
        }
    }

    private EntityBuilder addPrevLinkIfRequired(final EntityBuilder builder) {
        if (hotels.getActualPagination().getOffset() > 0) {
            final int prevOffset = Math.max(hotels.getActualPagination().getOffset() - Pagination.DEFAULT.getLimit(), 0);
            return builder.addLink(LinkBuilder.newInstance().
                    setHref(hrefWithOffset(prevOffset)).
                    setComponentClass("hotels").
                    setRelationship(Link.RELATIONSHIP_PREVIOUS).
                    build());
        }
        else {
            return builder;
        }
    }

    private String selfHrefWithOffset() {
        return hrefWithOffset(hotels.getActualPagination().getOffset());
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
