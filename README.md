real-rest
=========
![Build status](https://travis-ci.org/flushdia/real-rest.svg?branch=master)

# Build Guidelines

Project is built using [Gradle](http://www.gradle.org/).
Version of the Gradle used during build is specified in
[gradle-wrapper.properties](https://github.com/flushdia/let-us-take-a-REST/blob/master/gradle/wrapper/gradle-wrapper.properties).

It is assumed that commands below are run from the root of the project, i.e. root of the cloned repository.
README refers to this directory as a `$PROJECT_DIR`.

All commands are \*nix-specific. Use `gradlew` instead of `./gradlew` to run Gradle on Windows.

## Prerequisites

Install [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
and set `JAVA_HOME` environment variable to point to the installed JDK.

## Initial Build

`./gradlew build`

Build command compiles sources, processes resources, runs unit tests, runs a check and builds a WAR file.
WAR file is produced under `$PROJECT_DIR/build/libs` directory.
Name of the WAR file is a concatenation of the name of the project and its version with a hyphen in between:
`let-us-take-a-REST-1.0.war`

## Clean Rebuild

In case you need to rebuild the project from scratch ignoring
previously produced build caches and output directories, use the following command:

`./gradlew clean build`

## Running Integration Tests

`./gradlew integrationTest`

`integrationTest` task starts Tomcat container, runs integration tests and
closes Tomcat once tests have finished running.

Right now stop command produces the following exception:

    Failed to check for ThreadLocal references for web application [/let-us-take-a-REST]
    java.lang.NullPointerException
            at org.apache.catalina.loader.WebappClassLoader.loadedByThisOrChild(WebappClassLoader.java:2636)
            at org.apache.catalina.loader.WebappClassLoader.checkThreadLocalMapForLeaks(WebappClassLoader.java:2552)
            at org.apache.catalina.loader.WebappClassLoader.checkThreadLocalsForLeaks(WebappClassLoader.java:2507)
            at org.apache.catalina.loader.WebappClassLoader.clearReferences(WebappClassLoader.java:2013)
            at org.apache.catalina.loader.WebappClassLoader.stop(WebappClassLoader.java:1908)
            at org.apache.catalina.loader.WebappLoader.stopInternal(WebappLoader.java:661)
            at org.apache.catalina.util.LifecycleBase.stop(LifecycleBase.java:232)
            at org.apache.catalina.core.StandardContext.stopInternal(StandardContext.java:5702)
            at org.apache.catalina.util.LifecycleBase.stop(LifecycleBase.java:232)
            at org.apache.catalina.Lifecycle$stop.call(Unknown Source)
            at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:42)
            at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:108)
            at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:112)
            at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callSafe(AbstractCallSite.java:75)
            at org.gradle.api.plugins.tomcat.embedded.BaseTomcatServerImpl.stop(BaseTomcatServerImpl.groovy:43)
            at org.gradle.api.plugins.tomcat.embedded.TomcatServer$stop.call(Unknown Source)
            at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:42)
            at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:108)
            at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:112)
            at org.gradle.api.plugins.tomcat.internal.ShutdownMonitor.run(ShutdownMonitor.groovy:75)

This exception refers to a memory leak that is relevant for redeployment scenario.
Read more about it [here](http://stackoverflow.com/questions/7788280/memory-leak-when-redeploying-application-in-tomcat)
and [here](http://stackoverflow.com/questions/9992526/what-are-these-warnings-in-catalina-out)

This is NOT a severe problem for the purpose of this example project and it does not affect status of the build.
Successful build should finish with 0 process exit code and `BUILD SUCCESSFUL` message 
(usually seen BEFORE `NullPointerException`).

# Setting up Project in IntelliJ IDEA

## Prerequisites

Install required plugins:
* [Lombok](http://plugins.jetbrains.com/plugin/6317?pr=idea)

You may also enable built-in *Tomcat and TomEE Integration* plugin
to run Apache Tomcat directly from IntelliJ IDEA.

## Setup

 * Open IntelliJ IDEA.
 * Import Gradle project using *File > Import Project* menu:
 ** Point to a project's *build.gradle* file in *Select File or Directory to Import* screen. 
 ** Choose *Use customizable gradle wrapper* in *Import Project from Gradle* screen.
 * Make sure that *Project SDK* is set to JDK 8 and project language level is set to *8.0*.
 * Enable Lombok support and annotation processing for the compiler in project settings.
 * Build the project from the IDE.
 