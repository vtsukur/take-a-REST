package org.realrest.presentation

import spock.lang.Specification

import javax.ws.rs.client.ClientBuilder;

/**
 * @author volodymyr.tsukur
 */
class BookingsResourceSpecification extends Specification {

  def 'retrieve bookings'() {
    when:
    def client = ClientBuilder.newClient()
    def response = client.target("http://localhost:8080/realrest/api").request().buildGet().invoke()

    then:
    response
  }

}
