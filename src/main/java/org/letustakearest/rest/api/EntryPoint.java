package org.letustakearest.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author volodymyr.tsukur
 */
@Path("/")
public class EntryPoint {

    @GET
    @Produces("text/plain")
    public String helloWorld() {
        return "Hello, world!";
    }

}
