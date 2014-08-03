package org.letustakearest.infrastructure.jaxrs;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theoryinpractise.halbuilder.json.JsonRepresentationWriter;

import java.net.URI;
import java.util.Set;

/**
 * @author volodymyr.tsukur
 */
public class CustomJsonRepresentationWriter extends JsonRepresentationWriter {

    @Override
    protected JsonFactory getJsonFactory(final Set<URI> flags) {
        final JsonFactory reference = super.getJsonFactory(flags);
        final ObjectMapper mapper = (ObjectMapper) reference.getCodec();
        CustomObjectMapperContextResolver.customize(mapper);
        return reference;
    }

}
