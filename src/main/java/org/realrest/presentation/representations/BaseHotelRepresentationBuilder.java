package org.realrest.presentation.representations;

import com.google.code.siren4j.component.Link;
import com.google.code.siren4j.component.builder.EntityBuilder;
import com.google.code.siren4j.component.builder.LinkBuilder;
import org.realrest.domain.Hotel;
import org.realrest.presentation.resources.HotelsResource;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
abstract class BaseHotelRepresentationBuilder {

    protected final Hotel hotel;

    protected final UriInfo uriInfo;

    public BaseHotelRepresentationBuilder(final Hotel hotel, final UriInfo uriInfo) {
        this.hotel = hotel;
        this.uriInfo = uriInfo;
    }

    protected EntityBuilder builder() {
        return EntityBuilder.newInstance().
                setComponentClass("hotel").
                addProperty("id", hotel.getId()).
                addProperty("name", hotel.getName()).
                addLink(
                        LinkBuilder.newInstance().
                                setHref(selfHref()).
                                setRelationship(Link.RELATIONSHIP_SELF).
                                build());
    }

    protected String selfHref() {
        return selfURI().toString();
    }

    private URI selfURI() {
        return selfURI(hotel, uriInfo);
    }

    private static URI selfURI(final Hotel hotel, final UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().
                path(HotelsResource.class).
                path("item").
                path(hotel.getId().toString()).
                build();
    }

}
