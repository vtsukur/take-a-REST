package org.letustakearest.presentation.representations.siren;

import com.google.code.siren4j.component.Entity;
import com.google.code.siren4j.component.builder.ActionBuilder;
import com.google.code.siren4j.component.builder.EntityBuilder;
import com.google.code.siren4j.component.builder.FieldBuilder;
import com.google.code.siren4j.component.impl.ActionImpl;
import com.google.code.siren4j.meta.FieldType;
import org.letustakearest.domain.Hotel;
import org.letustakearest.presentation.resources._BookingsResource;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author volodymyr.tsukur
 */
class HotelRepresentationBuilder extends BaseHotelRepresentationBuilder {

    HotelRepresentationBuilder(final Hotel hotel, final UriInfo uriInfo) {
        super(hotel, uriInfo);
    }

    public Entity build() {
        return builder().
                addSubEntities(rooms()).
                build();
    }

    private List<Entity> rooms() {
        return hotel.getPlaces().stream().
                map(room -> EntityBuilder.newInstance().
                        setRelationship("hotel-room").
                        addProperty("type", room.getCategory().name().toLowerCase()).
                        addProperty("price", room.getPrice()).
                        addAction(ActionBuilder.newInstance().
                                setName("book").
                                setComponentClass("booking").
                                setMethod(ActionImpl.Method.POST).
                                setHref(uriInfo.getBaseUriBuilder().path(_BookingsResource.class).build().toString()).
                                setType(MediaType.APPLICATION_JSON).
                                addField(
                                        FieldBuilder.newInstance().
                                                setName("roomId").
                                                setType(FieldType.TEXT).
                                                setValue(room.getId().toString()).
                                                build()).
                                addFields(new SaveBookingFieldsBuilder().build()).
                                build()).
                        build()).
                collect(Collectors.toList());
    }

}
