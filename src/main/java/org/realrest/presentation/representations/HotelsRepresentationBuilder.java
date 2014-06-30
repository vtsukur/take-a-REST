package org.realrest.presentation.representations;

import com.google.code.siren4j.component.Entity;
import com.google.code.siren4j.component.Link;
import com.google.code.siren4j.component.builder.EntityBuilder;
import com.google.code.siren4j.component.builder.LinkBuilder;
import org.realrest.application.service.Pagination;
import org.realrest.domain.Hotel;
import org.realrest.domain.PaginatedResult;
import org.realrest.presentation.resources.HotelsResource;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author volodymyr.tsukur
 */
public class HotelsRepresentationBuilder {

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
                    setRelationship("next").
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
                    setRelationship("prev").
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
        return String.format("%s?offset=%d", baseSelfHref(), offset);
    }

    private String baseSelfHref() {
        return baseSelfURI().toString();
    }

    private URI baseSelfURI() {
        return baseSelfURI(uriInfo);
    }

    private static URI baseSelfURI(final UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().
                path(HotelsResource.class).
                build();
    }

}
