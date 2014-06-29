package org.realrest.presentation.representations;

import com.google.code.siren4j.component.Entity;
import com.google.code.siren4j.component.Link;
import com.google.code.siren4j.component.builder.EntityBuilder;
import com.google.code.siren4j.component.builder.LinkBuilder;
import org.realrest.domain.Hotel;
import org.realrest.presentation.resources.HotelsResource;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;

/**
 * @author volodymyr.tsukur
 */
public class HotelsRepresentationBuilder {

    private final Collection<Hotel> hotels;

    private final UriInfo uriInfo;

    public HotelsRepresentationBuilder(final Collection<Hotel> hotels, final UriInfo uriInfo) {
        this.hotels = hotels;
        this.uriInfo = uriInfo;
    }

    public Entity build() {
        EntityBuilder entityBuilder = EntityBuilder.newInstance().
                setComponentClass("hotels").
                addLink(LinkBuilder.newInstance().
                        setHref(selfHref()).
                        setRelationship(Link.RELATIONSHIP_SELF).
                        build());
        for (final Hotel hotel : hotels) {
            entityBuilder = entityBuilder.addSubEntity(
                    new HotelEmbeddedRepresentationBuilder(hotel, uriInfo).build()
            );
        }
        return entityBuilder.build();
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

}
