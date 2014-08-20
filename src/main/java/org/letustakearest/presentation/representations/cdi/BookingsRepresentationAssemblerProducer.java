package org.letustakearest.presentation.representations.cdi;

import org.letustakearest.presentation.representations.BookingsRepresentationAssembler;
import org.letustakearest.presentation.representations.hal.HalBookingsRepresentationAssembler;

import javax.enterprise.inject.Produces;
import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class BookingsRepresentationAssemblerProducer
        extends BaseAssemblerProducer<BookingsRepresentationAssembler> {

    @Produces
    @SelectByAcceptHeader
    public BookingsRepresentationAssembler produce() {
        return doProduce();
    }

    @Override
    protected BookingsRepresentationAssembler hal(final UriInfo uriInfo) {
        return new HalBookingsRepresentationAssembler(uriInfo);
    }

}
