package org.takearest.presentation.representations.cdi;

import org.takearest.presentation.representations.BookingRepresentationAssembler;
import org.takearest.presentation.representations.hal.HalBookingRepresentationAssembler;

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
