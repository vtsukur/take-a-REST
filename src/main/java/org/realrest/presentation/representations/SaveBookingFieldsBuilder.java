package org.realrest.presentation.representations;

import com.google.code.siren4j.component.Field;
import com.google.code.siren4j.component.builder.FieldBuilder;
import com.google.code.siren4j.meta.FieldType;

import java.util.Arrays;
import java.util.List;

/**
 * @author volodymyr.tsukur
 */
public class SaveBookingFieldsBuilder {

    public List<Field> build() {
        return Arrays.asList(
                FieldBuilder.newInstance().
                        setName("data.from").
                        setType(FieldType.DATE).
                        build(),
                FieldBuilder.newInstance().
                        setName("data.to").
                        setType(FieldType.DATE).
                        build(),
                FieldBuilder.newInstance().
                        setName("data.includeBreakfast").
                        setType(FieldType.CHECKBOX).
                        build());
    }

}
