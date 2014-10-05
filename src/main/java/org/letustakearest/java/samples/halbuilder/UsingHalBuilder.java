package org.letustakearest.java.samples.halbuilder;

import com.theoryinpractise.halbuilder.api.Representation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import org.letustakearest.infrastructure.jaxrs.CustomRepresentationFactory;
import org.letustakearest.java.samples.BookingRepresentationProducer;

/**
 * @author volodymyr.tsukur
 */
public class UsingHalBuilder {

    public static void main(final String[] args) {
        new UsingHalBuilder().demo();
    }

    public void demo() {
        RepresentationFactory representationFactory = new CustomRepresentationFactory();
        Representation representation = representationFactory.newRepresentation().
                withNamespace("take-a-rest", "http://localhost:8080/api/doc/rels/{rel}").
                withProperty("paid", false).
                withProperty("null", null).
                withLink("take-a-rest:hotel", "http://localhost:8080/api/hotels/12345").
                withLink("take-a-rest:get-hotel", "http://localhost:8080/api/hotels/12345").
                withRepresentation("take-a-rest:booking",
                        representationFactory.newRepresentation().
                                withBean(new BookingRepresentationProducer().newSample()).
                                withLink("take-a-rest:hotel", "http://localhost:8080/api/hotels/12345")).
                withBean(new BookingRepresentationProducer().newSample());
        System.out.println(representation.toString(RepresentationFactory.HAL_JSON));
    }

}
