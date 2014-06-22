package org.realrest.presentation

import org.realrest.domain.Booking
import org.realrest.infrastructure.rest.jaxrs.transitions.CreateBookingTransition

import javax.ws.rs.client.Entity
import javax.ws.rs.core.MediaType
import java.time.LocalDate

/**
 * @author volodymyr.tsukur
 */
class BookingsResourceSpecification extends BaseSpecification {

  def 'should create booking which is then discoverable by individual URI'() {
    given:
    def booking = new CreateBookingTransition(
        roomId: 1,
        from: LocalDate.of(2014, 8, 1),
        to: LocalDate.of(2014, 8, 15),
        includeBreakfast: true
    )
    def response

    when:
    response = client.target(uri('/api/bookings')).
        request().
        buildPost(Entity.entity(booking, MediaType.APPLICATION_JSON_TYPE)).
        invoke()
    response.close()

    then:
    201 == response.status
    def bookingURI = response.location
    bookingURI

    when:
    def actualBooking = client.target(bookingURI).request().get(Booking)

    then:
    actualBooking
    actualBooking.id
    booking.from.equals(actualBooking.from)
    booking.to.equals(actualBooking.to)
    booking.includeBreakfast.equals(actualBooking.includeBreakfast)
  }

}
