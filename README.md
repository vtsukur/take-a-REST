real-rest
=========
![Build status](https://travis-ci.org/flushdia/take-a-REST.svg?branch=master)

# Build Guidelines

Project is built using [Gradle](http://www.gradle.org/).
Version of the Gradle used during build is specified in
[gradle-wrapper.properties](https://github.com/flushdia/let-us-take-a-REST/blob/master/gradle/wrapper/gradle-wrapper.properties).

It is assumed that commands below are run from the root of the project, i.e. root of the cloned repository.
We'll refer to this directory with `$PROJECT_DIR` variable later in this document.

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

`integrationTest` task starts Apache Tomcat web container, runs integration tests and
then stops container once tests have finished running.

Right now procedure of stopping Apache Tomcat is accompanied with the following exception:

    Failed to check for ThreadLocal references for web application []
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

This exception refers to a memory leak actual for redeployment scenario.
Read more about it [here](http://stackoverflow.com/questions/7788280/memory-leak-when-redeploying-application-in-tomcat)
and [here](http://stackoverflow.com/questions/9992526/what-are-these-warnings-in-catalina-out)

This is NOT a severe problem for the purpose of this example project.
It does not affect status of the build.
Successful build should finish with 0 process exit code and `BUILD SUCCESSFUL` message,
usually seen right BEFORE `NullPointerException`.

## Running Web Application

`./gradlew tomcatRunWar`

`tomcatRunWar` task starts Apache Tomcat web container with the deployed web application,
accessible under [http://localhost:8080](http://localhost:8080) URL.

API entry point resides under [http://localhost:8080/api](http://localhost:8080/api) URL.

Container can be stopped by hitting CTRL+C.

Guidelines for changing Apache Tomcat configuration can be found [here](https://github.com/bmuschko/gradle-tomcat-plugin).

# Setting up Project in IntelliJ IDEA

## Prerequisites

Project was tested on IntelliJ IDEA 13.1.3 on Windows and Mac OS X.

Required plugins:
* [Lombok](http://plugins.jetbrains.com/plugin/6317?pr=idea)

You may also enable built-in *Tomcat and TomEE Integration* plugin
to run Apache Tomcat directly from IntelliJ IDEA.

## General Project Setup

 * Open IntelliJ IDEA.
 * Import Gradle project using *File > Import Project* menu:
     * Point to a project's *build.gradle* file in *Select File or Directory to Import* screen. 
     * Choose *Use customizable gradle wrapper* in *Import Project from Gradle* screen.
 * Make sure that *Project SDK* is set to JDK 8 and project language level is set to *8.0*.
 * Enable Lombok support and annotation processing for the compiler in project settings.
 * Build the project from the IDE.

## Running Web Application

### Configure Application Server
 
 * Use *Settings > Application Servers* screen.
 * Register *Tomcat Server* using Apache Tomcat 7.0.54 or higher
(requires [downloading it](http://tomcat.apache.org/download-70.cgi) and unpacking beforehand).

### Setup Run Configuration

 * Open *Run/Debug Configurations* screen.
 * Click *Add New Configuration* button using *Tomcat Server > Local* configuration type.
 * Choose configured Apache Tomcat application server.
 * Configure deployment:
     * Run configuration will warn that no deployment artifacts are marked for deployment.
Click *Fix* button choosing one of the suggested Gradle WAR artifacts.
 * (Optional) Specify name of the run configuration.

### Run Web Application

 * Choose prepared configuration and execute it by doing a *Run* or *Debug*.
 * Application should be deployed to the root context URL: [http://localhost:8080](http://localhost:8080).
