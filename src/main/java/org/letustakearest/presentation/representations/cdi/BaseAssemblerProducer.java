package org.letustakearest.presentation.representations.cdi;

import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Variant;
import java.util.List;

/**
 * @author volodymyr.tsukur
 */
abstract class BaseAssemblerProducer<T> {

    private static final MediaType GENERIC_HAL = MediaType.valueOf(RepresentationFactory.HAL_JSON);

    private static final MediaType GENERIC_SIREN = MediaType.valueOf("application/vnd.siren+json");

    private static final MediaType HOTEL_SIREN_V2 = MediaType.valueOf("application/vnd.siren.hotel.v2+json");

    private static final List<Variant> VARIANTS = Variant.mediaTypes(
            GENERIC_HAL, GENERIC_SIREN, HOTEL_SIREN_V2
    ).build();

    protected T doProduce() {
        final Request request = ResteasyProviderFactory.getContextData(Request.class);
        final Variant variant = request.selectVariant(VARIANTS);
        if (variant == null) {
            throw new RuntimeException("Only HAL and Siren media types are supported");
        }
        else {
            final UriInfo uriInfo = ResteasyProviderFactory.getContextData(UriInfo.class);
            if (variant.getMediaType().equals(GENERIC_HAL)) {
                return hal(uriInfo);
            }
            else {
                return siren(uriInfo);
            }
        }
    }

    abstract protected T hal(UriInfo uriInfo);

    abstract protected T siren(UriInfo uriInfo);

}
