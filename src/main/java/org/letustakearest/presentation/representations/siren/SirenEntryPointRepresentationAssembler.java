package org.letustakearest.presentation.representations.siren;

import com.google.code.siren4j.component.Link;
import com.google.code.siren4j.component.builder.EntityBuilder;
import com.google.code.siren4j.component.builder.LinkBuilder;
import org.letustakearest.presentation.representations.EntryPointRepresentationAssembler;
import org.letustakearest.presentation.resources.EntryPointResource;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
public class SirenEntryPointRepresentationAssembler extends BaseSirenRepresentationAssembler
        implements EntryPointRepresentationAssembler {

    public SirenEntryPointRepresentationAssembler(final UriInfo uriInfo) {
        super(uriInfo);
    }

    public Object assemble() {
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
                addLink(LinkBuilder.newInstance().
                        setHref(BookingsRepresentationBuilder.selfURI(uriInfo).toString()).
                        setRelationship("bookings").
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
