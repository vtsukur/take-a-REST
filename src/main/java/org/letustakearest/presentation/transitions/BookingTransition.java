package org.letustakearest.presentation.transitions;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public class BookingTransition {

    @NotNull
    private LocalDate checkIn;

    @NotNull
    private LocalDate checkOut;

    private Boolean includeBreakfast;

}
