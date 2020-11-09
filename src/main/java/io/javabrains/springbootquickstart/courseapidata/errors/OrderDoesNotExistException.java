package io.javabrains.springbootquickstart.courseapidata.errors;

import java.util.NoSuchElementException;

public class OrderDoesNotExistException extends NoSuchElementException {
    private static final long serialVersionUID = -123456784L;

    public OrderDoesNotExistException(String message)
    {
        super(message);
    }
}