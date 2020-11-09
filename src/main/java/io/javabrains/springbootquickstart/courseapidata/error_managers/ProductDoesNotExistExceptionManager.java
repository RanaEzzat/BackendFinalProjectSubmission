package io.javabrains.springbootquickstart.courseapidata.error_managers;

import io.javabrains.springbootquickstart.courseapidata.errors.NoDataFoundException;
import io.javabrains.springbootquickstart.courseapidata.errors.NonAdminAccessException;
import io.javabrains.springbootquickstart.courseapidata.errors.ProductDoesNotExistException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ProductDoesNotExistExceptionManager implements ExceptionMapper<ProductDoesNotExistException> {
    @Override
    public Response toResponse(ProductDoesNotExistException ex)
    {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),404,"http://localhost:8080");
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
    }

}