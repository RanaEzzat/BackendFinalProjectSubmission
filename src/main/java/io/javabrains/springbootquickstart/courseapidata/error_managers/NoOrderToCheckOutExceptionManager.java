package io.javabrains.springbootquickstart.courseapidata.error_managers;

import io.javabrains.springbootquickstart.courseapidata.errors.NoDataFoundException;
import io.javabrains.springbootquickstart.courseapidata.errors.NoOrderToCheckOutException;
import io.javabrains.springbootquickstart.courseapidata.errors.NonAdminAccessException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoOrderToCheckOutExceptionManager implements ExceptionMapper<NoOrderToCheckOutException> {
    @Override
    public Response toResponse(NoOrderToCheckOutException ex)
    {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),404,"http://localhost:8080");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
    }

}