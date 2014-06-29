package org.realrest.presentation

import groovy.json.JsonSlurper
import org.realrest.presentation.transitions.CreateBookingTransition
import org.realrest.presentation.transitions.PayForBookingTransition
import org.skyscreamer.jsonassert.JSONAssert

import javax.ws.rs.client.Entity
import javax.ws.rs.core.MediaType
import java.time.LocalDate

/**
 * @author volodymyr.tsukur
 */
class BookingsResourceSpecification extends BaseSpecification {

  def 'should create booking discoverable by individual URI and then pay for it'() {
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
        post(Entity.entity(transition, MediaType.APPLICATION_JSON))
    response.close()

    then:
    201 == response.status
    def bookingURI = response.location
    bookingURI

    when:
    def createdBookingPayload = client.target(bookingURI).request().get(String)

    then:
    JSONAssert.assertEquals(loadTemplate("booking-created.json", [
        bookingURI: bookingURI
    ]), createdBookingPayload, false)
    def createdBooking = new JsonSlurper().parseText(createdBookingPayload) as Map
    def paymentAction = (createdBooking.actions as List).get(0) as Map
    paymentAction

    when:
    response = client.target(paymentAction.href as String).
        request().
        method(paymentAction.method as String, Entity.entity(new PayForBookingTransition(
            cardholdersName: 'Viktor Yanukovych',
            creditCardNumber: '1234 5678 9012 3456',
            cvv: 123
        ), MediaType.APPLICATION_JSON))
    def paidBookingPayload = response.readEntity(String)
    response.close()

    then:
    200 == response.status
    JSONAssert.assertEquals(loadTemplate("booking-paid.json", [
        bookingURI: bookingURI
    ]), paidBookingPayload, false)
  }

  def 'should create booking and then cancel it'() {
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
        post(Entity.entity(transition, MediaType.APPLICATION_JSON))
    response.close()

    then:
    201 == response.status
    def bookingURI = response.location
    bookingURI

    when:
    def createdBookingPayload = client.target(bookingURI).request().get(String)

    then:
    JSONAssert.assertEquals(loadTemplate("booking-created.json", [
        bookingURI: bookingURI
    ]), createdBookingPayload, false)
    def createdBooking = new JsonSlurper().parseText(createdBookingPayload) as Map
    def cancelAction = (createdBooking.actions as List).get(1) as Map
    cancelAction

    when:
    response = client.target(cancelAction.href as String).
        request().
        method(cancelAction.method as String)
    response.close()

    then:
    204 == response.status

    when:
    response = client.target(bookingURI).request().get()
    response.close()

    then:
    404 == response.status
  }

  def 'should respond with 404 when booking does not exist'() {
    when:
    def response = client.target(uri('/api/bookings/item/0')).request().get()
    response.close()

    then:
    404 == response.status
  }

}
