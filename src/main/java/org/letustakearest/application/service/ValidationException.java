package org.letustakearest.application.service;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * @author volodymyr.tsukur
 */
@Getter
public class ValidationException extends RuntimeException {

    private final Set<ConstraintViolation<Object>> violations;

    private final String action;

    public ValidationException(Set<ConstraintViolation<Object>> violations, String action) {
        this.violations = violations;
        this.action = action;
    }

}
