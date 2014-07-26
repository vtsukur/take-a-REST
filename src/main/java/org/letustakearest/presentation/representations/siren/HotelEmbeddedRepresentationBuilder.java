package org.letustakearest.presentation.representations.siren;

import com.google.code.siren4j.component.Entity;
import com.google.code.siren4j.component.builder.LinkBuilder;
import org.letustakearest.domain.Hotel;
import org.letustakearest.presentation.resources.HotelsResource;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
class HotelEmbeddedRepresentationBuilder extends BaseHotelRepresentationBuilder {

    public HotelEmbeddedRepresentationBuilder(final Hotel hotel, final UriInfo uriInfo) {
        super(hotel, uriInfo);
    }

    public Entity build() {
        return builder().
                setRelationship("hotel").
                addLink(LinkBuilder.newInstance().
                        setHref(selfHref()).
                        setRelationship("hotel-as-place-via-media-type").
                        build()).
                addLink(LinkBuilder.newInstance().
                        setHref(selfHrefAsPlace()).
                        setRelationship("hotel-as-place-via-uri").
                        build()).
                build();
    }

    protected String selfHrefAsPlace() {
        return selfURIAsPlace().toString();
    }

    private URI selfURIAsPlace() {
        return selfURIAsPlace(hotel, uriInfo);
    }

    static URI selfURIAsPlace(final Hotel hotel, final UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().
                path(HotelsResource.class).
                path(hotel.getId().toString()).
                path("as-place").
                build();
    }

}
