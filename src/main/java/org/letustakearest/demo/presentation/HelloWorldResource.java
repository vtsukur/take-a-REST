package org.letustakearest.demo.presentation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author volodymyr.tsukur
 */
@Path("/hello-world")
public class HelloWorldResource {

    @GET
    @Produces("text/plain")
    public String helloWorld() {
        return "Hello, World!";
    }

}
