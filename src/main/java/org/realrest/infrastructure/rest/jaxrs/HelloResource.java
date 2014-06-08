package org.realrest.infrastructure.rest.jaxrs;

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

}
