package org.letustakearest.rest.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
* @author volodymyr.tsukur
*/
@ApplicationPath("/api")
public class RESTApplication extends Application {

    private final Set<Class<?>> classes = new HashSet<>();

    public RESTApplication() {
        classes.add(EntryPoint.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

}
