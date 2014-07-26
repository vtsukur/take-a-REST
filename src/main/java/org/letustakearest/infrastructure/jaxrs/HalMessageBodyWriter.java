package org.letustakearest.infrastructure.jaxrs;

import com.theoryinpractise.halbuilder.api.ReadableRepresentation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import static com.theoryinpractise.halbuilder.api.RepresentationFactory.HAL_JSON;

/**
 * @author volodymyr.tsukur
 */
@Provider
@Produces(RepresentationFactory.HAL_JSON)
public class HalMessageBodyWriter implements MessageBodyWriter<ReadableRepresentation> {

    @Override
    public boolean isWriteable(final Class type, final Type genericType, final Annotation[] annotations,
            final MediaType mediaType) {
        return ReadableRepresentation.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(final ReadableRepresentation representation, final Class type, final Type genericType,
            final Annotation[] annotations, final MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(final ReadableRepresentation representation, final Class<?> type, final Type genericType,
            final Annotation[] annotations, final MediaType mediaType, final MultivaluedMap<String, Object> httpHeaders,
            final OutputStream entityStream) throws IOException, WebApplicationException {
        representation.toString(HAL_JSON, new OutputStreamWriter(entityStream, "UTF-8"));
    }

}
