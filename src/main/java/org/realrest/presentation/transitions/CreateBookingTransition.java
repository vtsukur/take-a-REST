package org.realrest.presentation.transitions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public class CreateBookingTransition {

    private Long roomId;

    private LocalDate from;

    private LocalDate to;

    private boolean includeBreakfast;

}
