package org.realrest.presentation.transitions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public class BookingData {

    private LocalDate from;

    private LocalDate to;

    private Boolean includeBreakfast;

}
