package org.letustakearest.presentation.representations.hal;

import org.letustakearest.presentation.representations.EntryPointRepresentationAssembler;
import org.letustakearest.presentation.resources._BookingsResource;
import org.letustakearest.presentation.resources._EntryPointResource;
import org.letustakearest.presentation.resources._HotelsResource;

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
        return newRepresentation(_EntryPointResource.selfURI(uriInfo)).
                withLink(rel("hotels"), _HotelsResource.selfURI(uriInfo)).
                withLink(rel("bookings"), _BookingsResource.selfURI(uriInfo));
    }

}
