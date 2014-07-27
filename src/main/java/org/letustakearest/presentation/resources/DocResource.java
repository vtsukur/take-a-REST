package org.letustakearest.presentation.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.InputStream;

/**
 * @author volodymyr.tsukur
 */
@Path("/doc")
public class DocResource {

    @GET
    @Path("/{rel}")
    @Produces("text/html")
    public InputStream read(@PathParam("rel") final String rel) {
        return getClass().getResourceAsStream("/hal/doc/relations.html");
    }

}
