package org.takearest.presentation.representations.cdi;

import org.takearest.presentation.representations.HotelsRepresentationAssembler;
import org.takearest.presentation.representations.hal.HalHotelsRepresentationAssembler;

import javax.enterprise.inject.Produces;
import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class HotelsRepresentationAssemblerProducer
        extends BaseAssemblerProducer<HotelsRepresentationAssembler> {

    @Produces
    @SelectByAcceptHeader
    public HotelsRepresentationAssembler produce() {
        return doProduce();
    }

    @Override
    protected HotelsRepresentationAssembler hal(final UriInfo uriInfo) {
        return new HalHotelsRepresentationAssembler(uriInfo);
    }

}
