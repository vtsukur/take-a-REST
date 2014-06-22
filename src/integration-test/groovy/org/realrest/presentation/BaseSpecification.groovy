package org.realrest.presentation

import spock.lang.Specification

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder

/**
 * @author volodymyr.tsukur
 */
class BaseSpecification extends Specification {

  protected Client client

  def setup() {
    client = ClientBuilder.newClient()
  }

  protected static String uri(String relative) {
    "http://localhost:8080/realrest$relative"
  }

}
