package org.takearest.infrastructure.jaxrs;

import com.theoryinpractise.halbuilder.DefaultRepresentationFactory;
import com.theoryinpractise.halbuilder.json.JsonRepresentationReader;

/**
 * @author volodymyr.tsukur
 */
public class CustomRepresentationFactory extends DefaultRepresentationFactory {

    public CustomRepresentationFactory() {
        withRenderer(HAL_JSON, CustomJsonRepresentationWriter.class);
        withReader(HAL_JSON, JsonRepresentationReader.class);
    }

}
