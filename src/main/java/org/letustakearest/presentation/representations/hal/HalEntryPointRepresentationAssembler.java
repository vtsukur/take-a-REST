package org.letustakearest.presentation.representations.hal;

import org.letustakearest.presentation.representations.EntryPointRepresentationAssembler;
import org.letustakearest.presentation.resources.BookingsResource;
import org.letustakearest.presentation.resources.EntryPointResource;
import org.letustakearest.presentation.resources.HotelsResource;

import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class HalEntryPointRepresentationAssembler extends BaseHalRepresentationAssembler
        implements EntryPointRepresentationAssembler {

    public HalEntryPointRepresentationAssembler(final UriInfo uriInfo) {
        super(uriInfo);
    }

    @Override
    public Object assemble() {
        return representationFactory.
                newRepresentation(EntryPointResource.selfURI(uriInfo)).
                withLink("hotels", HotelsResource.selfURI(uriInfo)).
                withLink("bookings", BookingsResource.selfURI(uriInfo));
    }

}
