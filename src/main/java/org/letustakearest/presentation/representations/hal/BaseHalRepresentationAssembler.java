package org.letustakearest.presentation.representations.hal;

import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.standard.StandardRepresentationFactory;

import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
abstract class BaseHalRepresentationAssembler {

    protected final UriInfo uriInfo;

    protected final RepresentationFactory representationFactory = new StandardRepresentationFactory();

    public BaseHalRepresentationAssembler(final UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

}
