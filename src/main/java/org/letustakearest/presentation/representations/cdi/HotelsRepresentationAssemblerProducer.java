package org.letustakearest.presentation.representations.cdi;

import org.letustakearest.presentation.representations.HotelsRepresentationAssembler;
import org.letustakearest.presentation.representations.siren.SirenHotelsRepresentationAssembler;

import javax.enterprise.inject.Produces;
import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class HotelsRepresentationAssemblerProducer extends BaseAssemblerProducer<HotelsRepresentationAssembler> {

    @Produces
    @SelectByAcceptHeader
    public HotelsRepresentationAssembler produce() {
        return doProduce();
    }

    @Override
    protected HotelsRepresentationAssembler siren(final UriInfo uriInfo) {
        return new SirenHotelsRepresentationAssembler(uriInfo);
    }

}
