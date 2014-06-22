package org.realrest.infrastructure.rest.jaxrs.providers;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author volodymyr.tsukur
 */
@Path("bookings")
public class BookingsResource {

    @POST
    @Produces("application/vnd.siren+json")
    public String createBooking() {
        return "CREATED";
    }

}
