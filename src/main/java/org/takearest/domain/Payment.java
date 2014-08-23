package org.takearest.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public final class Payment extends IdentifiableAndVersioned {

    private String cardholdersName;

    private String creditCardNumber;

    private Integer amount;

    private Booking booking;

}
