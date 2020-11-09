package io.javabrains.springbootquickstart.courseapidata.errors;

import java.util.NoSuchElementException;

public class ProductDoesNotExistException extends NoSuchElementException {
    private static final long serialVersionUID = -123456785L;

    public ProductDoesNotExistException(String message)
    {
        super(message);
    }
}
