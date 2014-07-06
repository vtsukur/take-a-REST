package org.letustakearest.presentation.representations;

import com.google.code.siren4j.component.Entity;
import com.google.code.siren4j.component.builder.ActionBuilder;
import com.google.code.siren4j.component.builder.EntityBuilder;
import com.google.code.siren4j.component.builder.FieldBuilder;
import com.google.code.siren4j.component.impl.ActionImpl;
import com.google.code.siren4j.meta.FieldType;
import org.letustakearest.domain.Booking;

import javax.ws.rs.core.UriInfo;

/**
 * @author volodymyr.tsukur
 */
public class BookingRepresentationBuilder extends BaseBookingRepresentationBuilder {

    public BookingRepresentationBuilder(final Booking booking, final UriInfo uriInfo) {
        super(booking, uriInfo);
    }

    public Entity build() {
        return addCancellationActionIfAvailable(
                        addPaymentActionIfAvailable(
                                addUpdateActionIfAvailable(builder()))).
                build();
    }

    private EntityBuilder addUpdateActionIfAvailable(final EntityBuilder entityBuilder) {
        if (booking.getState() == Booking.State.CREATED) {
            return entityBuilder.addAction(
                    ActionBuilder.newInstance().
                            setName("update").
                            setComponentClass("booking").
                            setMethod(ActionImpl.Method.POST).
                            setHref(selfHref()).
                            addFields(new SaveBookingFieldsBuilder().build()).
                            build());
        }
        else {
            return entityBuilder;
        }
    }

    private EntityBuilder addPaymentActionIfAvailable(final EntityBuilder entityBuilder) {
        if (booking.getState() == Booking.State.CREATED) {
            return entityBuilder.addAction(
                    ActionBuilder.newInstance().
                            setName("pay").
                            setComponentClass("payment").
                            setMethod(ActionImpl.Method.POST).
                            setHref(selfHref() + "/payment").
                            setType("application/json").
                            addField(
                                    FieldBuilder.newInstance().
                                            setName("cardholdersName").
                                            build()).
                            addField(
                                    FieldBuilder.newInstance().
                                            setName("creditCardNumber").
                                            build()).
                            addField(
                                    FieldBuilder.newInstance().
                                            setName("cvv").
                                            setType(FieldType.NUMBER).
                                            build()).
                            build());
        }
        else {
            return entityBuilder;
        }
    }

    private EntityBuilder addCancellationActionIfAvailable(final EntityBuilder entityBuilder) {
        if (booking.getState() == Booking.State.CREATED ||
                booking.getState() == Booking.State.PAID) {
            return entityBuilder.addAction(
                    ActionBuilder.newInstance().
                            setName("cancel").
                            setComponentClass("booking").
                            setMethod(ActionImpl.Method.DELETE).
                            setHref(selfHref()).
                            build());
        }
        else {
            return entityBuilder;
        }
    }

}
