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

import static com.google.code.siren4j.Siren4J.JSON_MEDIATYPE
import static com.theoryinpractise.halbuilder.api.RepresentationFactory.HAL_JSON
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

  def 'should find hotel room, book it, update booking and then pay for it using Siren'() {
    given:
    def startingPoint = uri('/api')
    def response

    when:
    def entryPointPayload = request(startingPoint, JSON_MEDIATYPE).get(String)

    then:
    def entryPoint = assertTemplateNotStrict('entryPoint.json', JSON_MEDIATYPE, entryPointPayload)
    def hotelsURI = entryPoint.links?.find({ it.rel.contains('hotels') })?.href as String
    hotelsURI
    def bookingsURI = entryPoint.links?.find({ it.rel.contains('bookings') })?.href as String
    bookingsURI

    when:
    def hotelsPage1Payload = request(hotelsURI, JSON_MEDIATYPE).get(String)

    then:
    def hotelsPage1 = assertTemplateNotStrict('hotels-page1.json', JSON_MEDIATYPE, hotelsPage1Payload)
    def nextHotelsPageURI = hotelsPage1.links?.find({ it.rel.contains('next') })?.href as String
    nextHotelsPageURI

    when:
    def hotelsPage2Payload = request(nextHotelsPageURI, JSON_MEDIATYPE).get(String)

    then:
    def hotelsPage2 = assertTemplateNotStrict('hotels-page2.json', JSON_MEDIATYPE, hotelsPage2Payload)
    def hotelURI = hotelsPage2.entities?.get(0)?.links?.find({ it.rel.contains('self') })?.href as String
    hotelURI

    when:
    def hotelPayload = request(hotelURI, JSON_MEDIATYPE).get(String)

    then:
    def hotel = assertTemplateNotStrict('hotel.json', JSON_MEDIATYPE, hotelPayload)
    def bookingAction = hotel.entities?.get(0)?.actions?.find({ it.name == 'book' })
    bookingAction

    when:
    response = close(request(bookingAction.href as String, JSON_MEDIATYPE).post(
        entity(
            referenceCreateBookingTransition(bookingAction.fields?.find({ it.name == 'roomId' })?.value as Long),
            APPLICATION_JSON)))

    then:
    201 == response.status
    def bookingURI = response.location
    bookingURI

    when:
    response = request(bookingURI, JSON_MEDIATYPE).get()

    then:
    def createdBookingPayload = response.readEntity(String)
    def createdBookingETag = response.entityTag.value
    createdBookingETag
    def createdBooking = assertTemplateNotStrict('booking-created.json', JSON_MEDIATYPE, createdBookingPayload, [
        bookingURI: bookingURI
    ])
    def updateAction = createdBooking?.actions?.find({ it.name == 'update' })
    updateAction

    when:
    response = request(bookingURI, JSON_MEDIATYPE).header("If-None-Match", createdBookingETag).get()

    then:
    Response.Status.NOT_MODIFIED.statusCode == response.status

    when:
    response = request(updateAction.href as String, JSON_MEDIATYPE).
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
    def updatedBooking = assertTemplateNotStrict('booking-updated.json', JSON_MEDIATYPE, updatedBookingPayload, [
        bookingURI: bookingURI
    ])
    def paymentAction = updatedBooking?.actions?.find({ it.name == 'pay' })
    paymentAction

    when:
    response = request(paymentAction.href as String, JSON_MEDIATYPE).
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
        assertTemplateNotStrict('booking-paid.json', JSON_MEDIATYPE, paidBookingPayload, [
        bookingURI: bookingURI
    ])

    when:
    def bookings = toJson(request(bookingsURI, JSON_MEDIATYPE).get(String))

    then:
    bookings.entities?.find({
      it.links?.find({
        it.rel?.contains('self') && it.href == bookingURI.toString()
      })
    })
  }

  def 'should find hotel room, book it, update booking and then pay for it using HAL'() {
    given:
    def startingPoint = uri('/api')
    def response

    when:
    def entryPointPayload = request(startingPoint, HAL_JSON).get(String)

    then:
    def entryPoint = assertTemplateNotStrict('entryPoint.json', HAL_JSON, entryPointPayload)
    def hotelsURI = entryPoint._links?.get('get-some-rest:hotels')?.href as String
    hotelsURI
    def bookingsURI = entryPoint._links?.get('get-some-rest:bookings')?.href as String
    bookingsURI

    when:
    def hotelsPage1Payload = request(hotelsURI, HAL_JSON).get(String)

    then:
    def hotelsPage1 = assertTemplateNotStrict('hotels-page1.json', HAL_JSON, hotelsPage1Payload)
    def nextHotelsPageURI = hotelsPage1._links?.get('next')?.href as String
    nextHotelsPageURI

    when:
    def hotelsPage2Payload = request(nextHotelsPageURI, HAL_JSON).get(String)

    then:
    def hotelsPage2 = assertTemplateNotStrict('hotels-page2.json', HAL_JSON, hotelsPage2Payload)
    def hotelURI = (hotelsPage2._embedded?.get('get-some-rest:hotel') as List)[0]._links?.get('self')?.href as String
    hotelURI

    when:
    def hotelPayload = request(hotelURI, HAL_JSON).get(String)

    then:
    def hotel = assertTemplateNotStrict('hotel.json', HAL_JSON, hotelPayload)
    hotel
    def room = (hotel?._embedded?.get('get-some-rest:room') as List)[0]
    room
    def bookingsLink = room?._links?.get('get-some-rest:bookings')?.href as String
    bookingsLink

    when:
    response = close(request(bookingsLink, HAL_JSON).post(
        entity(
            referenceCreateBookingTransition(room.id as Long),
            APPLICATION_JSON)))

    then:
    201 == response.status
    def bookingURI = response.location
    bookingURI

    when:
    response = request(bookingURI, JSON_MEDIATYPE).get()

    then:
    def createdBookingPayload = response.readEntity(String)
    def createdBookingETag = response.entityTag.value
    createdBookingETag
    def createdBooking = assertTemplateNotStrict('booking-created.json', JSON_MEDIATYPE, createdBookingPayload, [
        bookingURI: bookingURI
    ])
    def updateAction = createdBooking?.actions?.find({ it.name == 'update' })
    updateAction

    when:
    response = request(bookingURI, JSON_MEDIATYPE).header("If-None-Match", createdBookingETag).get()

    then:
    Response.Status.NOT_MODIFIED.statusCode == response.status

    when:
    response = request(updateAction.href as String, JSON_MEDIATYPE).
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
    def updatedBooking = assertTemplateNotStrict('booking-updated.json', JSON_MEDIATYPE, updatedBookingPayload, [
        bookingURI: bookingURI
    ])
    def paymentAction = updatedBooking?.actions?.find({ it.name == 'pay' })
    paymentAction

    when:
    response = request(paymentAction.href as String, JSON_MEDIATYPE).
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
        assertTemplateNotStrict('booking-paid.json', JSON_MEDIATYPE, paidBookingPayload, [
        bookingURI: bookingURI
    ])

    when:
    def bookings = toJson(request(bookingsURI, JSON_MEDIATYPE).get(String))

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
    response = close(request(uri('/api/bookings'), JSON_MEDIATYPE).
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
    def createdBookingPayload = request(bookingURI, JSON_MEDIATYPE).get(String)

    then:
    def createdBooking = assertTemplateNotStrict('booking-created.json', JSON_MEDIATYPE, createdBookingPayload, [
        bookingURI: bookingURI
    ])
    def cancelAction = createdBooking?.actions?.find({ it.name == 'cancel'})
    cancelAction

    when:
    response = close(request(cancelAction.href as String, JSON_MEDIATYPE).method(cancelAction.method as String))

    then:
    204 == response.status

    when:
    response = close(request(bookingURI, JSON_MEDIATYPE).get())

    then:
    404 == response.status
  }

  def 'should forbid creation of invalid booking'() {
    given:
    def response

    when:
    response = request(uri('/api/bookings'), JSON_MEDIATYPE).
        post(entity(new CreateBookingTransition(
            roomId: null,
            data: null
        ), APPLICATION_JSON))
    def bookingCreationErrorPayload = response.readEntity(String)

    then:
    400 == response.status
    assertTemplateNotStrict('invalid-booking-NOT-created.json', JSON_MEDIATYPE, bookingCreationErrorPayload)
  }

  def 'should respond with 404 when booking does not exist'() {
    when:
    def response = request(uri('/api/bookings/0'), JSON_MEDIATYPE).get()

    then:
    404 == response.status
  }

  private Invocation.Builder request(String href, String mediaType) {
    client.target(href).request().accept(mediaType)
  }

  private Invocation.Builder request(URI uri, String mediaType) {
    client.target(uri).request().accept(mediaType)
  }

  private static String uri(String relative = '') {
    "http://localhost:8080$relative"
  }

  private static assertTemplateNotStrict(String template, String mediaType, String payload, Map binding = [:]) {
    JSONAssert.assertEquals(loadTemplate(template, mediaType, [ baseURI: uri() ] << binding), payload, false)
    toJson(payload)
  }

  private static String loadTemplate(String name, String mediaType, Map binding) {
    new SimpleTemplateEngine().createTemplate(ApiSpecification.getResource("${mediaTypeToDirName(mediaType)}/$name")).make(binding).toString()
  }

  private static String mediaTypeToDirName(String mediaType) {
    HAL_JSON.equals(mediaType) ? 'hal' : 'siren'
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
