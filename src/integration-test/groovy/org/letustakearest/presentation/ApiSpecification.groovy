package org.letustakearest.presentation

import groovy.json.JsonSlurper
import groovy.text.SimpleTemplateEngine
import org.letustakearest.presentation.transitions.BookingData
import org.letustakearest.presentation.transitions.CreateBookingTransition
import org.letustakearest.presentation.transitions.PayForBookingTransition
import org.letustakearest.presentation.transitions.UpdateBookingTransition
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
class ApiSpecification extends Specification {

  private Client client

  def setup() {
    client = ClientBuilder.newClient()
  }

  def 'should find hotel room, book it, update booking and then pay for it'() {
    given:
    def startingPoint = uri('/api')
    def response

    when:
    def entryPointPayload = request(startingPoint).get(String)

    then:
    def entryPoint = assertTemplateNotStrict('entryPoint.json', entryPointPayload)
    def hotelsURI = entryPoint.links?.find({ it.rel.contains('hotels') })?.href as String
    hotelsURI
    def bookingsURI = entryPoint.links?.find({ it.rel.contains('bookings') })?.href as String
    bookingsURI

    when:
    def hotelsPage1Payload = request(hotelsURI).get(String)

    then:
    def hotelsPage1 = assertTemplateNotStrict('hotels-page1.json', hotelsPage1Payload)
    def nextHotelsPageURI = hotelsPage1.links?.find({ it.rel.contains('next') })?.href as String
    nextHotelsPageURI

    when:
    def hotelsPage2Payload = request(nextHotelsPageURI).get(String)

    then:
    def hotelsPage2 = assertTemplateNotStrict('hotels-page2.json', hotelsPage2Payload)
    def hotelURI = hotelsPage2.entities?.get(0)?.links?.find({ it.rel.contains('self') })?.href as String
    hotelURI

    when:
    def hotelPayload = request(hotelURI).get(String)

    then:
    def hotel = assertTemplateNotStrict('hotel.json', hotelPayload)
    def bookingAction = hotel.entities?.get(0)?.actions?.find({ it.name == 'book' })
    bookingAction

    when:
    response = close(request(bookingAction.href as String).post(
        entity(
            referenceCreateBookingTransition(bookingAction.fields?.find({ it.name == 'roomId' })?.value as Long),
            APPLICATION_JSON)))

    then:
    201 == response.status
    def bookingURI = response.location
    bookingURI

    when:
    response = request(bookingURI).get()

    then:
    def createdBookingPayload = response.readEntity(String)
    def createdBookingETag = response.entityTag.value
    createdBookingETag
    def createdBooking = assertTemplateNotStrict('booking-created.json', createdBookingPayload, [
        bookingURI: bookingURI
    ])
    def updateAction = createdBooking?.actions?.find({ it.name == 'update' })
    updateAction

    when:
    response = request(bookingURI).header("If-None-Match", createdBookingETag).get()

    then:
    Response.Status.NOT_MODIFIED.statusCode == response.status

    when:
    response = request(updateAction.href as String).
        header('If-Match', createdBookingETag).
        method(
            updateAction.method as String,
            entity(new UpdateBookingTransition(
                data: new BookingData(
                    from: LocalDate.of(2014, 8, 1),
                    to: LocalDate.of(2014, 8, 20),
                    includeBreakfast: false
                )
            ), APPLICATION_JSON))

    then:
    def updatedBookingETag = response.entityTag.value
    createdBookingETag != updatedBookingETag
    def updatedBookingPayload = response.readEntity(String)
    def updatedBooking = assertTemplateNotStrict('booking-updated.json', updatedBookingPayload, [
        bookingURI: bookingURI
    ])
    def paymentAction = updatedBooking?.actions?.find({ it.name == 'pay' })
    paymentAction

    when:
    response = request(paymentAction.href as String).
        header('If-Match', updatedBookingETag).
        method(paymentAction.method as String, entity(new PayForBookingTransition(
            cardholdersName: 'Viktor Yanukovych',
            creditCardNumber: '1234 5678 9012 3456',
            cvv: 123
        ), APPLICATION_JSON))

    then:
    def paidBookingETag = response.entityTag.value
    updatedBookingETag != paidBookingETag
    def paidBookingPayload = response.readEntity(String)
        assertTemplateNotStrict('booking-paid.json', paidBookingPayload, [
        bookingURI: bookingURI
    ])

    when:
    def bookings = toJson(request(bookingsURI).get(String))

    then:
    bookings.entities?.find({
      it.links?.find({
        it.rel?.contains('self') && it.href == bookingURI.toString()
      })
    })
  }

  def 'should create booking and then cancel it'() {
    given:
    def response

    when:
    response = close(request(uri('/api/bookings')).
        post(entity(new CreateBookingTransition(
            roomId: 1,
            data: new BookingData(
              from: LocalDate.of(2014, 8, 1),
              to: LocalDate.of(2014, 8, 15),
              includeBreakfast: true
            )
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

  def 'should forbid creation of invalid booking'() {
    given:
    def response

    when:
    response = close(request(uri('/api/bookings')).
        post(entity(new CreateBookingTransition(
            roomId: null,
            data: null
        ), APPLICATION_JSON)))

    then:
    500 == response.status
  }

  def 'should respond with 404 when booking does not exist'() {
    when:
    def response = request(uri('/api/bookings/0')).get()

    then:
    404 == response.status
  }

  private Invocation.Builder request(String href) {
    client.target(href).request()
  }

  private Invocation.Builder request(URI uri) {
    client.target(uri).request()
  }

  private static String uri(String relative = '') {
    "http://localhost:8080$relative"
  }

  private static String loadTemplate(String name, Map binding) {
    new SimpleTemplateEngine().createTemplate(ApiSpecification.getResource(name)).make(binding).toString()
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

  private static CreateBookingTransition referenceCreateBookingTransition(final Long roomId = 1L) {
    new CreateBookingTransition(
        roomId: roomId,
        data: new BookingData(
            from: LocalDate.of(2014, 8, 1),
            to: LocalDate.of(2014, 8, 15),
            includeBreakfast: true
        )
    )
  }

}
