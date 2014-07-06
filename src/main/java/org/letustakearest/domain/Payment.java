package org.letustakearest.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public final class Payment extends Identifiable {

    private String cardholdersName;

    private String creditCardNumber;

    private Integer amount;

    private Booking booking;

}
