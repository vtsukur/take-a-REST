package org.realrest.infrastructure.rest.jaxrs.resources;

import com.theoryinpractise.halbuilder.api.ReadableRepresentation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;

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

/**
 * Based on the implementation of
 * <a href="https://github.com/HalBuilder/halbuilder-jaxrs/blob/develop/src/main/java/com/theoryinpractise/halbuilder/jaxrs/JaxRsHalBuilderSupport.java">com.theoryinpractise.halbuilder.jaxrs.JaxRsHalBuilderSupport</a>.
 *
 * @author volodymyr.tsukur
 */
@Provider
public final class HalMessageBodyWriter implements MessageBodyWriter {

    private static final MediaType HAL_JSON_MEDIA_TYPE = MediaType.valueOf(RepresentationFactory.HAL_JSON);

    @Override
    public boolean isWriteable(final Class type, final Type genericType, final Annotation[] annotations,
            final MediaType mediaType) {
        return ReadableRepresentation.class.isAssignableFrom(type) && mediaType.isCompatible(HAL_JSON_MEDIA_TYPE);
    }

    @Override
    public long getSize(final Object t, final Class type, final Type genericType, final Annotation[] annotations,
            final MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(final Object t, final Class type, final Type genericType, final Annotation[] annotations,
            final MediaType mediaType, final MultivaluedMap httpHeaders, final OutputStream entityStream)
            throws IOException, WebApplicationException {
        final ReadableRepresentation representation = (ReadableRepresentation) t;
        representation.toString(mediaType.toString(), new OutputStreamWriter(entityStream));
    }

}
