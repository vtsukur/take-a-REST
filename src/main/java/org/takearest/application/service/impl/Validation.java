package org.takearest.application.service.impl;

import org.takearest.application.service.ValidationException;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author volodymyr.tsukur
 */
public class Validation {

    @Inject
    private ValidatorFactory validatorFactory;

    public void validate(final Object object, final String action) throws ValidationException {
        final Validator validator = validatorFactory.getValidator();
        final Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations, action);
        }
    }

}
