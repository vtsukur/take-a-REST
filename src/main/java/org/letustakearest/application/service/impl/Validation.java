package org.letustakearest.application.service.impl;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author volodymyr.tsukur
 */
public class Validation {

    @Inject
    private ValidatorFactory validatorFactory;

    public void validate(final Object object) throws ConstraintViolationException {
        final Validator validator = validatorFactory.getValidator();
        final Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}
