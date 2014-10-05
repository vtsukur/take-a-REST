package org.letustakearest.java.samples;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public class BookingRepresentation {

    private LocalDate checkIn;

    private LocalDate checkOut;

    private boolean includeBreakfast;

    private BigDecimal price;

}
