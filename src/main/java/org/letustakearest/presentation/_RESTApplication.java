//package org.letustakearest.presentation;
//
//import org.letustakearest.presentation.resources._BookingsResource;
//import org.letustakearest.presentation.resources._DocResource;
//import org.letustakearest.presentation.resources._EntryPointResource;
//import org.letustakearest.presentation.resources._HotelsResource;
//
//import javax.ws.rs.ApplicationPath;
//import javax.ws.rs.core.Application;
//import java.util.HashSet;
//import java.util.Set;
//
///**
//* @author volodymyr.tsukur
//*/
//@ApplicationPath("/api")
//public class _RESTApplication extends Application {
//
//    private final Set<Class<?>> classes = new HashSet<>();
//
//    public _RESTApplication() {
//        classes.add(_EntryPointResource.class);
//        classes.add(_DocResource.class);
//        classes.add(_HotelsResource.class);
//        classes.add(_BookingsResource.class);
//    }
//
//    public Set<Class<?>> getClasses() {
//        return classes;
//    }
//
//}
