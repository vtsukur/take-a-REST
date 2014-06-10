package org.realrest.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public final class User extends Identifiable {

    private Set<Booking> bookings;

}
