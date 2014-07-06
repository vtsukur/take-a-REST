package org.letustakearest.domain;

/**
 * @author volodymyr.tsukur
 */
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(final String message) {
        super(message);
    }

    public EntityNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(final Throwable cause) {
        super(cause);
    }

    protected EntityNotFoundException(final String message, final Throwable cause, final boolean enableSuppression,
                                      final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
