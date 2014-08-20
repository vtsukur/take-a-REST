package org.letustakearest.presentation.representations.cdi;

import org.letustakearest.presentation.representations.BookingRepresentationAssembler;
import org.letustakearest.presentation.representations.hal.HalBookingRepresentationAssembler;

import javax.enterprise.inject.Produces;
import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class BookingRepresentationAssemblerProducer
        extends BaseAssemblerProducer<BookingRepresentationAssembler> {

    @Produces
    @SelectByAcceptHeader
    public BookingRepresentationAssembler produce() {
        return doProduce();
    }

    @Override
    protected BookingRepresentationAssembler hal(final UriInfo uriInfo) {
        return new HalBookingRepresentationAssembler(uriInfo);
    }

}
