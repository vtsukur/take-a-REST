package org.letustakearest.presentation.transitions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public final class CreateBookingTransition {

    private Long roomId;

    private BookingData data;

}
