package org.realrest.presentation.resources;

import com.google.code.siren4j.Siren4J;
import org.realrest.presentation.cache.CacheControlFactory;
import org.realrest.presentation.representations.EntryPointRepresentationBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
@Path("/")
public class EntryPointResource {

    @GET
    @Produces({ Siren4J.JSON_MEDIATYPE, MediaType.APPLICATION_JSON })
    public Response services(@Context final UriInfo uriInfo) {
        return Response.
                ok(new EntryPointRepresentationBuilder(uriInfo).build()).
                cacheControl(CacheControlFactory.oneDay()).
                build();
    }

}
