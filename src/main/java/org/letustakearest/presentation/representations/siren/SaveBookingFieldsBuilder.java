package org.letustakearest.presentation.representations.siren;

import com.google.code.siren4j.component.Field;
import com.google.code.siren4j.component.builder.FieldBuilder;
import com.google.code.siren4j.meta.FieldType;

import java.util.Arrays;
import java.util.List;

/**
 * @author volodymyr.tsukur
 */
class SaveBookingFieldsBuilder {

    public List<Field> build() {
        return Arrays.asList(
                FieldBuilder.newInstance().
                        setName("checkIn").
                        setType(FieldType.DATE).
                        build(),
                FieldBuilder.newInstance().
                        setName("checkOut").
                        setType(FieldType.DATE).
                        build(),
                FieldBuilder.newInstance().
                        setName("includeBreakfast").
                        setType(FieldType.CHECKBOX).
                        build());
    }

}
