package org.realrest.presentation

import org.realrest.infrastructure.rest.jaxrs.transitions.CreateBookingTransition

import javax.ws.rs.client.Entity
import javax.ws.rs.core.MediaType
import java.time.LocalDate

/**
 * @author volodymyr.tsukur
 */
class BookingsResourceSpecification extends BaseSpecification {

  def 'create booking'() {
    given:
    def booking = new CreateBookingTransition(
        roomId: 1,
        from: LocalDate.of(2014, 8, 1),
        to: LocalDate.of(2014, 8, 15),
        includeBreakfast: true
    )

    when:
    def response = client.target(uri('/api/bookings')).
        request().
        buildPost(Entity.entity(booking, MediaType.APPLICATION_JSON_TYPE)).
        invoke()

    then:
    201 == response.status

    cleanup:
    response.close()
  }

}
