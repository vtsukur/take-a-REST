package org.letustakearest.presentation.representations.cdi;

import com.google.code.siren4j.Siren4J;
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

    private static final MediaType GENERIC_HAL = MediaType.valueOf("application/hal+json");

    private static final MediaType GENERIC_SIREN = MediaType.valueOf(Siren4J.JSON_MEDIATYPE);

    private static final List<Variant> VARIANTS = Variant.mediaTypes(
            GENERIC_HAL, GENERIC_SIREN
    ).build();

    protected T doProduce() {
        final Request request = ResteasyProviderFactory.getContextData(Request.class);
        final Variant variant = request.selectVariant(VARIANTS);
        if (variant == null) {
            throw new RuntimeException("Only HAL and Siren media types are supported");
        }
        else {
            if (variant.getMediaType().equals(GENERIC_SIREN)) {
                return siren(ResteasyProviderFactory.getContextData(UriInfo.class));
            }
            else {
                throw new RuntimeException("Support for HAL media type is in progress - not ready yet");
            }
        }
    }

    abstract protected T siren(UriInfo uriInfo);

}
