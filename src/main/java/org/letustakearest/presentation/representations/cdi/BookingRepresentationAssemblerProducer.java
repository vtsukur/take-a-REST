package org.letustakearest.presentation.representations.cdi;

import org.letustakearest.presentation.representations.BookingRepresentationAssembler;
import org.letustakearest.presentation.representations.siren.SirenBookingRepresentationAssembler;

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
    protected BookingRepresentationAssembler siren(final UriInfo uriInfo) {
        return new SirenBookingRepresentationAssembler(uriInfo);
    }

}
