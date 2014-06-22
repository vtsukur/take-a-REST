package org.realrest.presentation

/**
 * @author volodymyr.tsukur
 */
class BookingsResourceSpecification extends BaseSpecification {

  def 'retrieve bookings'() {
    when:
    def response = client.target("http://localhost:8080/realrest/api").request().buildGet().invoke()

    then:
    response
  }

}
