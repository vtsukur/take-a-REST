package org.letustakearest.presentation.resources;

import com.google.code.siren4j.Siren4J;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import org.letustakearest.presentation.representations.EntryPointRepresentationAssembler;
import org.letustakearest.presentation.representations.cdi.SelectByAcceptHeader;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.time.Duration;

/**
 * @author volodymyr.tsukur
 */
@Path("/")
public class EntryPointResource {

    @Inject
    @SelectByAcceptHeader
    private EntryPointRepresentationAssembler entryPointRepresentationAssembler;

    @GET
    @Produces({ RepresentationFactory.HAL_JSON, Siren4J.JSON_MEDIATYPE })
    public Response services() {
        return Response.
                ok(entryPointRepresentationAssembler.assemble()).
                cacheControl(CacheControl.valueOf("max-age=" + Duration.ofDays(1).getSeconds())).
                build();
    }

    public static URI selfURI(final UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().
                path(EntryPointResource.class).
                build();
    }

}
