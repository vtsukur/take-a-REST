package org.realrest.infrastructure.rest.jaxrs.providers;

import com.theoryinpractise.halbuilder.standard.StandardRepresentationFactory;

import javax.enterprise.inject.Produces;

/**
 * @author volodymyr.tsukur
 */
public final class StandardRepresentationFactoryProducer {

    @Produces
    public StandardRepresentationFactory newFactory() {
        return new StandardRepresentationFactory();
    }

}
