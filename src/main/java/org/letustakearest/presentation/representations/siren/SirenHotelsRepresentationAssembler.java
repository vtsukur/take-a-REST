package org.letustakearest.presentation.representations.siren;

import org.letustakearest.domain.Hotel;
import org.letustakearest.domain.PaginatedResult;
import org.letustakearest.presentation.representations.HotelsRepresentationAssembler;

import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class SirenHotelsRepresentationAssembler extends BaseSirenRepresentationAssembler
        implements HotelsRepresentationAssembler {

    public SirenHotelsRepresentationAssembler(final UriInfo uriInfo) {
        super(uriInfo);
    }

    @Override
    public Object from(final PaginatedResult<Hotel> hotels) {
        return new HotelsRepresentationBuilder(hotels, uriInfo).build();
    }

}
