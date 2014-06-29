package org.realrest.presentation.representations;

import com.google.code.siren4j.component.Entity;
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
public class HotelEmbeddedRepresentationBuilder {

    private final Hotel hotel;

    private final UriInfo uriInfo;

    public HotelEmbeddedRepresentationBuilder(final Hotel hotel, final UriInfo uriInfo) {
        this.hotel = hotel;
        this.uriInfo = uriInfo;
    }

    public Entity build() {
        return EntityBuilder.newInstance().
                setComponentClass("hotel").
                setRelationship("hotel").
                addProperty("id", hotel.getId()).
                addProperty("name", hotel.getName()).
                addLink(
                        LinkBuilder.newInstance().
                                setHref(selfHref()).
                                setRelationship(Link.RELATIONSHIP_SELF).
                                build()).build();
    }

    private String selfHref() {
        return selfURI().toString();
    }

    private URI selfURI() {
        return selfURI(hotel, uriInfo);
    }

    public static URI selfURI(final Hotel hotel, final UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().
                path(HotelsResource.class).
                path("item").
                path(hotel.getId().toString()).
                build();
    }

}
