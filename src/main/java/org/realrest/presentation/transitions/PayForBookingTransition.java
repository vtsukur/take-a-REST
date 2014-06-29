package org.realrest.presentation.transitions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public final class PayForBookingTransition {

    private String cardholdersName;

    private String creditCardNumber;

    private String cvv;

}
