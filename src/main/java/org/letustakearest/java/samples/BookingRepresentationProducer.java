package org.letustakearest.java.samples;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author volodymyr.tsukur
 */
public class BookingRepresentationProducer {

    public BookingRepresentation newSample() {
        BookingRepresentation sampleBooking = new BookingRepresentation();
        sampleBooking.setCheckIn(LocalDate.of(2014, 11, 23));
        sampleBooking.setCheckOut(LocalDate.of(2014, 12, 1));
        sampleBooking.setIncludeBreakfast(true);
        sampleBooking.setPrice(BigDecimal.valueOf(10000));
        return sampleBooking;
    }

}
