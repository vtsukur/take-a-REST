package org.realrest.infrastructure.rest.jaxrs;

import org.realrest.infrastructure.rest.jaxrs.providers.EntryPointResource;
import org.realrest.infrastructure.rest.jaxrs.providers.HotelsResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author volodymyr.tsukur
 */
public class RESTApplication extends Application {

    private final Set<Class<?>> classes = new HashSet<>();

    public RESTApplication() {
        classes.add(EntryPointResource.class);
        classes.add(HotelsResource.class);
    }

    public Set<Class<?>> getClasses() {
        return classes;
    }

}
