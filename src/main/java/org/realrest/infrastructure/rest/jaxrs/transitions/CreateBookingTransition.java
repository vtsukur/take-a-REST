package org.realrest.infrastructure.rest.jaxrs.transitions;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * @author volodymyr.tsukur
 */
@Getter
@Setter
public class CreateBookingTransition {

    private Long roomId;

    private ZonedDateTime from;

    private ZonedDateTime to;

    private boolean includeBreakfast;

}
