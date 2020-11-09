package io.javabrains.springbootquickstart.courseapidata.error_managers;

import io.javabrains.springbootquickstart.courseapidata.errors.NoDataFoundException;
import io.javabrains.springbootquickstart.courseapidata.errors.NonAdminAccessException;
import io.javabrains.springbootquickstart.courseapidata.errors.UserDoesNotExistException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserDoesNotExistExceptionManager implements ExceptionMapper<UserDoesNotExistException> {
    @Override
    public Response toResponse(UserDoesNotExistException ex)
    {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),404,"http://localhost:8080");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
    }

}