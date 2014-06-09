package org.realrest.infrastructure.rest.jaxrs;

import com.theoryinpractise.halbuilder.api.Representation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.standard.StandardRepresentationFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author volodymyr.tsukur
 */
@Path("hello")
public class HelloResource {

    @GET
    @Produces("text/plain")
    public String world() {
        return "world";
    }

    @GET
    @Path("hal")
    @Produces(RepresentationFactory.HAL_JSON)
    public Representation representation() {
        final RepresentationFactory factory = new StandardRepresentationFactory();
        factory.withFlag(RepresentationFactory.PRETTY_PRINT);
        return factory.newRepresentation().withProperty("key", "value");
    }

}
