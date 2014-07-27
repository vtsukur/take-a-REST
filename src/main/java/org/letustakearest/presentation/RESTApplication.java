package org.letustakearest.presentation;

import org.letustakearest.presentation.resources.BookingsResource;
import org.letustakearest.presentation.resources.DocResource;
import org.letustakearest.presentation.resources.EntryPointResource;
import org.letustakearest.presentation.resources.HotelsResource;

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
        classes.add(EntryPointResource.class);
        classes.add(DocResource.class);
        classes.add(HotelsResource.class);
        classes.add(BookingsResource.class);
    }

    public Set<Class<?>> getClasses() {
        return classes;
    }

}
