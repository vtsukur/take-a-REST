package org.realrest.presentation.representations;

import com.google.code.siren4j.component.Entity;
import com.google.code.siren4j.component.Link;
import com.google.code.siren4j.component.builder.EntityBuilder;
import com.google.code.siren4j.component.builder.LinkBuilder;
import org.realrest.presentation.resources.EntryPointResource;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
public class EntryPointRepresentationBuilder {

    private UriInfo uriInfo;

    public EntryPointRepresentationBuilder(final UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    public Entity build() {
        return EntityBuilder.newInstance().
                setComponentClass("entry-point").
                addLink(LinkBuilder.newInstance().
                        setHref(selfHref()).
                        setRelationship(Link.RELATIONSHIP_SELF).
                        build()).
                addLink(LinkBuilder.newInstance().
                        setHref(HotelsRepresentationBuilder.baseSelfURI(uriInfo).toString()).
                        setRelationship("hotels").
                        build()).
                build();
    }

    private String selfHref() {
        return selfURI().toString();
    }

    private URI selfURI() {
        return selfURI(uriInfo);
    }

    private static URI selfURI(final UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().
                path(EntryPointResource.class).
                build();
    }

}
