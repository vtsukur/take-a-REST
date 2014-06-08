package org.realrest.infrastructure.rest.jaxrs;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
* @author volodymyr.tsukur
*/
public class RESTApplication extends Application {

    private final Set<Object> singletons = new HashSet<Object>();

    public RESTApplication() {
        singletons.add(new HelloResource());
    }

    public Set<Object> getSingletons() {
        return singletons;
    }

}
