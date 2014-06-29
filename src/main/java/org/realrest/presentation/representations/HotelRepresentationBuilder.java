package org.realrest.presentation.representations;

import com.google.code.siren4j.component.Entity;
import com.google.code.siren4j.component.builder.ActionBuilder;
import com.google.code.siren4j.component.builder.EntityBuilder;
import com.google.code.siren4j.component.impl.ActionImpl;
import org.realrest.domain.Hotel;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author volodymyr.tsukur
 */
public class HotelRepresentationBuilder extends BaseHotelRepresentationBuilder {

    public HotelRepresentationBuilder(final Hotel hotel, final UriInfo uriInfo) {
        super(hotel, uriInfo);
    }

    public Entity build() {
        return builder().
                addSubEntities(rooms()).
                build();
    }

    private List<Entity> rooms() {
        return hotel.getRooms().stream().
                map(room -> EntityBuilder.newInstance().
                        setRelationship("hotel-room").
                        addProperty("type", room.getType().name().toLowerCase()).
                        addProperty("price", room.getPrice()).
                        addAction(ActionBuilder.newInstance().
                                setName("book").
                                setComponentClass("booking").
                                setMethod(ActionImpl.Method.POST).
                                setHref("abc").
                                setType(MediaType.APPLICATION_JSON).
                                build()).
                        build()).
                collect(Collectors.toList());
    }

}
