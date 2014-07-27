package org.letustakearest.presentation.representations.siren;

import com.google.code.siren4j.component.Link;
import com.google.code.siren4j.component.builder.EntityBuilder;
import com.google.code.siren4j.component.builder.LinkBuilder;
import org.letustakearest.domain.Hotel;
import org.letustakearest.presentation.resources.HotelResource;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
class BaseHotelRepresentationBuilder {

    protected final Hotel hotel;

    protected final UriInfo uriInfo;

    public BaseHotelRepresentationBuilder(final Hotel hotel, final UriInfo uriInfo) {
        this.hotel = hotel;
        this.uriInfo = uriInfo;
    }

    protected EntityBuilder builder() {
        return EntityBuilder.newInstance().
                setComponentClass("hotel").
                addProperty("name", hotel.getName()).
                addLink(LinkBuilder.newInstance().
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

    static URI selfURI(final Hotel hotel, final UriInfo uriInfo) {
        return HotelResource.selfURI(hotel, uriInfo);
    }

}
