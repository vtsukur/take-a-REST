package org.realrest.domain;

/**
 * @author volodymyr.tsukur
 */
public class BookingNotFoundException extends Exception {

    public BookingNotFoundException() {
        super();
    }

    public BookingNotFoundException(final String message) {
        super(message);
    }

    public BookingNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BookingNotFoundException(final Throwable cause) {
        super(cause);
    }

    protected BookingNotFoundException(final String message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
