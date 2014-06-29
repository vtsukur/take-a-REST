package org.realrest.presentation.representations;

import com.google.code.siren4j.component.Entity;
import com.google.code.siren4j.component.Link;
import com.google.code.siren4j.component.builder.ActionBuilder;
import com.google.code.siren4j.component.builder.EntityBuilder;
import com.google.code.siren4j.component.builder.FieldBuilder;
import com.google.code.siren4j.component.builder.LinkBuilder;
import com.google.code.siren4j.component.impl.ActionImpl;
import com.google.code.siren4j.meta.FieldType;
import org.realrest.domain.Booking;
import org.realrest.presentation.resources.BookingsResource;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author volodymyr.tsukur
 */
public class BookingRepresentationBuilder {

    private final Booking booking;

    private final UriInfo uriInfo;

    public BookingRepresentationBuilder(final Booking booking, final UriInfo uriInfo) {
        this.booking = booking;
        this.uriInfo = uriInfo;
    }

    public Entity build() {
        EntityBuilder entityBuilder = EntityBuilder.newInstance().
                setComponentClass("booking").
                addProperty("id", booking.getId()).
                addProperty("from", booking.getFrom()).
                addProperty("to", booking.getTo()).
                addProperty("includeBreakfast", booking.isIncludeBreakfast()).
                addLink(
                        LinkBuilder.newInstance().
                                setHref(selfHref()).
                                setRelationship(Link.RELATIONSHIP_SELF).
                                build());
        entityBuilder = addPaymentActionIfAvailable(entityBuilder);
        entityBuilder = addCancellationActionIfAvailable(entityBuilder);
        return entityBuilder.build();
    }

    private EntityBuilder addPaymentActionIfAvailable(final EntityBuilder entityBuilder) {
        if (booking.getState() == Booking.State.CREATED) {
            return entityBuilder.addAction(
                    ActionBuilder.newInstance().
                            setName("payment").
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
        if (booking.getState() != Booking.State.CANCELLED) {
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

    private String selfHref() {
        return selfURI().toString();
    }

    private URI selfURI() {
        return selfURI(booking, uriInfo);
    }

    public static URI selfURI(final Booking booking, final UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().
                path(BookingsResource.class).
                path("item").
                path(booking.getId().toString()).
                build();
    }

}
