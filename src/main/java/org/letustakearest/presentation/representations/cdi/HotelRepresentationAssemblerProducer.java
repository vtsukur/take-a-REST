package org.letustakearest.presentation.representations.cdi;

import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.letustakearest.presentation.representations.HotelRepresentationAssembler;
import org.letustakearest.presentation.representations.SirenHotelRepresentationAssembler;

import javax.enterprise.inject.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Variant;
import java.util.List;

/**
 * @author volodymyr.tsukur
 */
public class HotelRepresentationAssemblerProducer {

    private static final MediaType GENERIC_SIREN = MediaType.valueOf("application/vnd.siren*+json");

    private static final MediaType GENERIC_HAL = MediaType.valueOf("application/hal*+json");

    private static final List<Variant> VARIANTS = Variant.mediaTypes(
            GENERIC_SIREN, GENERIC_HAL
    ).build();

    @Produces
    @SelectByAcceptHeader
    public HotelRepresentationAssembler produce() {
        final Request request = ResteasyProviderFactory.getContextData(Request.class);
        final Variant variant = request.selectVariant(VARIANTS);
        if (variant == null) {
            throw new RuntimeException("Only HAL and Siren media types are supported");
        }
        else {
            if (variant.getMediaType().equals(GENERIC_SIREN)) {
                final UriInfo uriInfo = ResteasyProviderFactory.getContextData(UriInfo.class);
                return new SirenHotelRepresentationAssembler(uriInfo);
            }
            else {
                throw new RuntimeException("Support for HAL media type is in progress - not ready yet");
            }
        }
    }

}
