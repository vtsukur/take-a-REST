package org.letustakearest.presentation.representations.siren;

import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
abstract class BaseSirenRepresentationAssembler {

    protected final UriInfo uriInfo;

    public BaseSirenRepresentationAssembler(final UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

}
