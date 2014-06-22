package org.realrest.presentation.representations;

import com.google.code.siren4j.component.Entity;
import com.google.code.siren4j.component.builder.EntityBuilder;
import org.realrest.domain.Booking;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author volodymyr.tsukur
 */
@ApplicationScoped
public class BookingRepresentationBuilder {

    public Entity prepare(final Booking booking) {
        return EntityBuilder.newInstance().
                setComponentClass("booking").
                addProperty("id", booking.getId()).
                addProperty("from", booking.getFrom()).
                addProperty("to", booking.getTo()).
                addProperty("includeBreakfast", booking.isIncludeBreakfast()).
                build();
    }

}
