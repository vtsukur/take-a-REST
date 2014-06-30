package org.realrest.presentation.representations;

import com.google.code.siren4j.component.Entity;
import com.google.code.siren4j.component.Link;
import com.google.code.siren4j.component.builder.EntityBuilder;
import com.google.code.siren4j.component.builder.LinkBuilder;
import org.realrest.application.service.Pagination;
import org.realrest.domain.Hotel;
import org.realrest.presentation.resources.HotelsResource;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author volodymyr.tsukur
 */
public class HotelsRepresentationBuilder {

    private final Collection<Hotel> hotels;

    private final Pagination pagination;

    private final int totalCount;

    private final UriInfo uriInfo;

    public HotelsRepresentationBuilder(final Collection<Hotel> hotels,
                                       final Pagination pagination,
                                       final int totalCount,
                                       final UriInfo uriInfo) {
        this.hotels = hotels;
        this.pagination = pagination;
        this.totalCount = totalCount;
        this.uriInfo = uriInfo;
    }

    public Entity build() {
        EntityBuilder builder = EntityBuilder.newInstance().
                setComponentClass("hotels").
                addProperty("offset", pagination.getOffset()).
                addProperty("limit", pagination.getLimit()).
                addProperty("total", totalCount).
                addSubEntities(hotels()).
                addLink(LinkBuilder.newInstance().
                        setHref(selfHref()).
                        setRelationship(Link.RELATIONSHIP_SELF).
                        build());
        return addNextLinkIfRequired(addPrevLinkIfRequired(builder)).build();
    }

    private List<Entity> hotels() {
        return hotels.stream().
                map(hotel -> new HotelEmbeddedRepresentationBuilder(hotel, uriInfo).build()).
                collect(Collectors.toList());
    }

    private String selfHref() {
        return selfURI().toString();
    }

    private URI selfURI() {
        return selfURI(uriInfo);
    }

    public static URI selfURI(final UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().
                path(HotelsResource.class).
                build();
    }

    private EntityBuilder addNextLinkIfRequired(final EntityBuilder builder) {
        return builder;
    }

    private EntityBuilder addPrevLinkIfRequired(final EntityBuilder builder) {
        return builder;
    }

}
