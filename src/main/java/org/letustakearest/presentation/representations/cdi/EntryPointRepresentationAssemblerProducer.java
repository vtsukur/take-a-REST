package org.letustakearest.presentation.representations.cdi;

import org.letustakearest.presentation.representations.EntryPointRepresentationAssembler;
import org.letustakearest.presentation.representations.hal.HalEntryPointRepresentationAssembler;

import javax.enterprise.inject.Produces;
import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class EntryPointRepresentationAssemblerProducer
        extends BaseAssemblerProducer<EntryPointRepresentationAssembler> {

    @Produces
    @SelectByAcceptHeader
    public EntryPointRepresentationAssembler produce() {
        return doProduce();
    }

    @Override
    protected EntryPointRepresentationAssembler hal(final UriInfo uriInfo) {
        return new HalEntryPointRepresentationAssembler(uriInfo);
    }

}
