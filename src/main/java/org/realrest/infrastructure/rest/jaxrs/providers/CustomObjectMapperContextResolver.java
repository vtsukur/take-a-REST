package org.realrest.infrastructure.rest.jaxrs.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * @author volodymyr.tsukur
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class CustomObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public CustomObjectMapperContextResolver() {
        mapper = new ObjectMapper();
        mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
        mapper.registerModule(new JSR310Module());
    }

    @Override
    public ObjectMapper getContext(final Class<?> type) {
        return mapper;
    }

}
