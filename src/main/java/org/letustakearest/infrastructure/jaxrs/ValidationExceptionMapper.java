package org.letustakearest.infrastructure.jaxrs;

import org.letustakearest.application.service.ValidationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author volodymyr.tsukur
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(final ValidationException exception) {
        final Error error = new Error();
        error.setType(exception.getClass().getSimpleName());
        error.setMessage(exception.getMessage());
        error.setAction(exception.getAction());
        return Response.
                status(Response.Status.BAD_REQUEST).
                entity(error).
                build();
    }

}
