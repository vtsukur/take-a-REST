package org.realrest.presentation

import groovy.text.SimpleTemplateEngine
import spock.lang.Specification

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Invocation

/**
 * @author volodymyr.tsukur
 */
abstract class BaseSpecification extends Specification {

  protected Client client

  def setup() {
    client = ClientBuilder.newClient()
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
    new SimpleTemplateEngine().createTemplate(BaseSpecification.getResource(name)).make(binding).toString()
  }

}
