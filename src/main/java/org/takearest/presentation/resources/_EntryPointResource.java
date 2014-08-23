package org.takearest.presentation.resources;

import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import org.takearest.presentation.cache.CacheControlFactory;
import org.takearest.presentation.representations.EntryPointRepresentationAssembler;
import org.takearest.presentation.representations.cdi.SelectByAcceptHeader;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
@Path("/")
public class _EntryPointResource {

    @Inject
    @SelectByAcceptHeader
    private EntryPointRepresentationAssembler entryPointRepresentationAssembler;

    @GET
    @Produces({ RepresentationFactory.HAL_JSON })
    public Response services() {
        return Response.
                ok(entryPointRepresentationAssembler.assemble()).
                cacheControl(CacheControlFactory.oneDay()).
                build();
    }

    public static URI selfURI(final UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().
                path(_EntryPointResource.class).
                build();
    }

}
