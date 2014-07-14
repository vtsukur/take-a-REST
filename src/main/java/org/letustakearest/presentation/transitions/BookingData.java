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
public class BookingData {

    @NotNull
    private LocalDate from;

    @NotNull
    private LocalDate to;

    private Boolean includeBreakfast;

}
