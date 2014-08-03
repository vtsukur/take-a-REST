package org.letustakearest.presentation.representations.hal;

import com.theoryinpractise.halbuilder.api.Representation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import org.letustakearest.infrastructure.jaxrs.CustomRepresentationFactory;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
abstract class BaseHalRepresentationAssembler {

    protected final static String NAMESPACE = "get-some-rest";

    protected final UriInfo uriInfo;

    protected final RepresentationFactory representationFactory = new CustomRepresentationFactory();

    public BaseHalRepresentationAssembler(final UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    protected Representation newRepresentation() {
        return representationFactory.newRepresentation();
    }

    protected Representation newRepresentation(final URI uri) {
        return representationFactory.newRepresentation(uri).
                withNamespace(NAMESPACE, curie());
    }

    protected Representation newRepresentation(final String href) {
        return representationFactory.newRepresentation(href).
                withNamespace(NAMESPACE, curie());
    }

    private String curie() {
        return uriInfo.getBaseUriBuilder().segment("doc").build().toString() + "/{rel}";
    }

    protected String rel(final String rel) {
        return NAMESPACE + ":" + rel;
    }

}
