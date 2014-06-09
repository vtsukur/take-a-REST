package org.realrest.infrastructure.rest.jaxrs;

import org.realrest.infrastructure.rest.jaxrs.providers.EntryPointResource;
import org.realrest.infrastructure.rest.jaxrs.providers.HelloResource;
import org.realrest.infrastructure.rest.jaxrs.providers.HotelsResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author volodymyr.tsukur
 */
public final class RESTApplication extends Application {

    private final Set<Object> singletons = new HashSet<Object>();

    public RESTApplication() {
        singletons.add(new HelloResource());
        singletons.add(new EntryPointResource());
        singletons.add(new HotelsResource());
    }

    public Set<Object> getSingletons() {
        return singletons;
    }

}
