package org.letustakearest.presentation.representations.cdi;

import org.letustakearest.presentation.representations.EntryPointRepresentationAssembler;
import org.letustakearest.presentation.representations.siren.SirenEntryPointRepresentationAssembler;

import javax.enterprise.inject.Produces;
import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class EntryPointRepresentationAssemblerProducer extends BaseAssemblerProducer<EntryPointRepresentationAssembler> {

    @Produces
    @SelectByAcceptHeader
    public EntryPointRepresentationAssembler produce() {
        return doProduce();
    }

    @Override
    protected EntryPointRepresentationAssembler siren(final UriInfo uriInfo) {
        return new SirenEntryPointRepresentationAssembler(uriInfo);
    }

}
