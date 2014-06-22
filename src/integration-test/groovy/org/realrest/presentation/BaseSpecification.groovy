package org.realrest.presentation

import spock.lang.Specification

import javax.ws.rs.client.ClientBuilder

/**
 * @author volodymyr.tsukur
 */
class BaseSpecification extends Specification {

  def client

  def setup() {
    client = ClientBuilder.newClient()
  }

}
