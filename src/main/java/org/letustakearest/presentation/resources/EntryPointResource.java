package org.letustakearest.presentation.resources;

import com.google.code.siren4j.Siren4J;
import org.letustakearest.presentation.cache.CacheControlFactory;
import org.letustakearest.presentation.representations.EntryPointRepresentationAssembler;
import org.letustakearest.presentation.representations.cdi.SelectByAcceptHeader;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
@Path("/")
public class EntryPointResource {

    @Inject
    @SelectByAcceptHeader
    private EntryPointRepresentationAssembler entryPointRepresentationAssembler;

    @GET
    @Produces({ Siren4J.JSON_MEDIATYPE })
    public Response services(@Context final UriInfo uriInfo) {
        return Response.
                ok(entryPointRepresentationAssembler.assemble()).
                cacheControl(CacheControlFactory.oneDay()).
                build();
    }

}
