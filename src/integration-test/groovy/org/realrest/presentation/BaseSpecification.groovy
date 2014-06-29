package org.realrest.presentation

import groovy.text.SimpleTemplateEngine
import spock.lang.Specification

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder

/**
 * @author volodymyr.tsukur
 */
abstract class BaseSpecification extends Specification {

  protected Client client

  def setup() {
    client = ClientBuilder.newClient()
  }

  protected static String uri(String relative) {
    "http://localhost:8080/realrest$relative"
  }

  protected static String loadTemplate(String name, Map binding) {
    new SimpleTemplateEngine().createTemplate(BaseSpecification.getResource(name)).make(binding).toString()
  }

}
