package org.letustakearest.presentation.representations.cdi;

import org.letustakearest.presentation.representations.HotelRepresentationAssembler;
import org.letustakearest.presentation.representations.siren.SirenHotelRepresentationAssembler;

import javax.enterprise.inject.Produces;
import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class HotelRepresentationAssemblerProducer
        extends BaseAssemblerProducer<HotelRepresentationAssembler> {

    @Produces
    @SelectByAcceptHeader
    public HotelRepresentationAssembler produce() {
        return doProduce();
    }

    @Override
    protected HotelRepresentationAssembler hal(final UriInfo uriInfo) {
        return null;
    }

    @Override
    protected HotelRepresentationAssembler siren(final UriInfo uriInfo) {
        return new SirenHotelRepresentationAssembler(uriInfo);
    }

}
