package org.realrest.infrastructure.rest.jaxrs.providers;

import com.theoryinpractise.halbuilder.api.Representation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.standard.StandardRepresentationFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author volodymyr.tsukur
 */
@Path("hotels")
public class HotelsResource {

    @Inject
    private StandardRepresentationFactory standardRepresentationFactory;

    @GET
    @Produces(RepresentationFactory.HAL_JSON)
    public Representation retrieve() {
        return standardRepresentationFactory.newRepresentation();
    }

}
