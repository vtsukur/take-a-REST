package org.letustakearest.presentation.transitions;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public final class CreateBookingAsPlaceTransition {

    @NotNull
    private Long placeId;

    @NotNull
    private BookingData data;

}
