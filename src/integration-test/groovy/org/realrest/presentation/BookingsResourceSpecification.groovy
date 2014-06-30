package org.realrest.presentation

import groovy.json.JsonSlurper
import groovy.text.SimpleTemplateEngine
import org.realrest.presentation.transitions.CreateBookingTransition
import org.realrest.presentation.transitions.PayForBookingTransition
import org.skyscreamer.jsonassert.JSONAssert
import spock.lang.Specification

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Invocation
import javax.ws.rs.core.Response
import java.time.LocalDate

import static javax.ws.rs.client.Entity.entity
import static javax.ws.rs.core.MediaType.APPLICATION_JSON

/**
 * @author volodymyr.tsukur
 */
class BookingsResourceSpecification extends Specification {

  protected Client client

  def setup() {
    client = ClientBuilder.newClient()
  }

  def 'should find first hotel room, book it and then pay for it'() {
    given:
    def startingPoint = uri('/api')
    def response

    when:
    def entryPointPayload = request(startingPoint).get(String)

    then:
    def entryPoint = assertTemplateNotStrict('entryPoint.json', entryPointPayload)
    def hotelsURI = entryPoint.links?.find({ it.rel.contains('hotels') })?.href as String
    hotelsURI

    when:
    def hotelsPayload = request(hotelsURI).get(String)

    then:
    def hotels = assertTemplateNotStrict('hotels.json', hotelsPayload)
    def hotelURI = hotels.entities?.get(0)?.links?.find({ it.rel.contains('self') })?.href as String
    hotelURI

    when:
    def hotelPayload = request(hotelURI).get(String)

    then:
    def hotel = assertTemplateNotStrict('hotel.json', hotelPayload)
    def bookingAction = hotel.entities?.get(0)?.actions?.find({ it.name == 'book' })
    bookingAction

    when:
    response = close(request(bookingAction.href as String).post(
        entity(new CreateBookingTransition(
            roomId: Long.parseLong(bookingAction.fields?.find({ it.name == 'roomId' })?.value as String),
            from: LocalDate.of(2014, 8, 1),
            to: LocalDate.of(2014, 8, 15),
            includeBreakfast: true
        ), APPLICATION_JSON)))

    then:
    201 == response.status
    def bookingURI = response.location
    bookingURI

    when:
    def createdBookingPayload = request(bookingURI).get(String)

    then:
    def createdBooking = assertTemplateNotStrict('booking-created.json', createdBookingPayload, [
        bookingURI: bookingURI
    ])
    def paymentAction = createdBooking?.actions?.find({ it.name == 'pay' })
    paymentAction

    when:
    def paidBookingPayload = request(paymentAction.href as String).
        method(paymentAction.method as String, entity(new PayForBookingTransition(
            cardholdersName: 'Viktor Yanukovych',
            creditCardNumber: '1234 5678 9012 3456',
            cvv: 123
        ), APPLICATION_JSON), String)

    then:
    assertTemplateNotStrict('booking-paid.json', paidBookingPayload, [
        bookingURI: bookingURI
    ])
  }

  def 'should create booking and then cancel it'() {
    given:
    def response

    when:
    response = close(client.target(uri('/api/bookings')).
        request().
        post(entity(new CreateBookingTransition(
            roomId: 1,
            from: LocalDate.of(2014, 8, 1),
            to: LocalDate.of(2014, 8, 15),
            includeBreakfast: true
        ), APPLICATION_JSON)))

    then:
    201 == response.status
    def bookingURI = response.location
    bookingURI

    when:
    def createdBookingPayload = request(bookingURI).get(String)

    then:
    def createdBooking = assertTemplateNotStrict('booking-created.json', createdBookingPayload, [
        bookingURI: bookingURI
    ])
    def cancelAction = createdBooking?.actions?.find({ it.name == 'cancel'})
    cancelAction

    when:
    response = close(request(cancelAction.href as String).method(cancelAction.method as String))

    then:
    204 == response.status

    when:
    response = close(request(bookingURI).get())

    then:
    404 == response.status
  }

  def 'should respond with 404 when booking does not exist'() {
    when:
    def response = request(uri('/api/bookings/0')).get()

    then:
    404 == response.status
  }

  protected Invocation.Builder request(String href) {
    client.target(href).request()
  }

  protected Invocation.Builder request(URI uri) {
    client.target(uri).request()
  }

  protected static String uri(String relative = '') {
    "http://localhost:8080/realrest$relative"
  }

  protected static String loadTemplate(String name, Map binding) {
    new SimpleTemplateEngine().createTemplate(BookingsResourceSpecification.getResource(name)).make(binding).toString()
  }

  private static assertTemplateNotStrict(String template, String payload, Map binding = [:]) {
    JSONAssert.assertEquals(loadTemplate(template, [ baseURI: uri() ] << binding), payload, false)
    toJson(payload)
  }

  private static Response close(Response response) {
    response.close()
    response
  }

  private static toJson(String text) {
    new JsonSlurper().parseText(text)
  }

}
