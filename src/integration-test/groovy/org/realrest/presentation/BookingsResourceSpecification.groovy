package org.realrest.presentation

/**
 * @author volodymyr.tsukur
 */
class BookingsResourceSpecification extends BaseSpecification {

  def 'create booking'() {
    when:
    def response = client.target(uri('/api')).request().buildGet().invoke()

    then:
    response
  }

}
