package org.realrest.presentation

import org.realrest.presentation.transitions.CreateBookingTransition

import javax.ws.rs.client.Entity
import javax.ws.rs.core.MediaType
import java.time.LocalDate
/**
 * @author volodymyr.tsukur
 */
class BookingsResourceSpecification extends BaseSpecification {

  def 'should create booking discoverable by individual URI'() {
    given:
    def transition = new CreateBookingTransition(
        roomId: 1,
        from: LocalDate.of(2014, 8, 1),
        to: LocalDate.of(2014, 8, 15),
        includeBreakfast: true
    )

    when:
    def response = client.target(uri('/api/bookings')).
        request().
        buildPost(Entity.entity(transition, MediaType.APPLICATION_JSON_TYPE)).
        invoke()
    response.close()

    then:
    201 == response.status
    def bookingURI = response.location
    bookingURI

    when:
    def actualBooking = client.target(bookingURI).request().get(Map)

    then:
    actualBooking
    def classes = actualBooking.get('class') as List
    classes?.size()
    classes.get(0) == 'booking'
    def properties = actualBooking.get('properties') as Map
    properties
    properties.get('id')
//    transition.from.equals(properties.get('from'))
//    transition.to.equals(properties.get('to'))
    transition.includeBreakfast.equals(properties.get('includeBreakfast'))
  }

  def 'should respond with 404 when booking does not exist'() {
    when:
    def response = client.target(uri('/api/bookings/item/0')).request().get()
    response.close()

    then:
    404 == response.status
  }

}
