package io.javabrains.springbootquickstart.courseapidata.errors;

import java.util.NoSuchElementException;

public class NoOrderToCheckOutException extends NoSuchElementException {
    private static final long serialVersionUID = -123456783L;

    public NoOrderToCheckOutException(String message)
    {
        super(message);
    }
}
