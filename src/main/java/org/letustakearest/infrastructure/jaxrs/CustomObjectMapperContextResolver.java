package org.letustakearest.infrastructure.jaxrs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.google.code.siren4j.Siren4J;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * @author volodymyr.tsukur
 */
@Provider
@Produces({ Siren4J.JSON_MEDIATYPE, MediaType.APPLICATION_JSON })
public class CustomObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public CustomObjectMapperContextResolver() {
        mapper = customize(new ObjectMapper());
    }

    @Override
    public ObjectMapper getContext(final Class<?> type) {
        return mapper;
    }

    static ObjectMapper customize(final ObjectMapper mapper) {
        mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
        mapper.registerModule(new JSR310Module());
        return mapper;
    }

}
