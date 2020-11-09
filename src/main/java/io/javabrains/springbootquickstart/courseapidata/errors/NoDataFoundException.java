package io.javabrains.springbootquickstart.courseapidata.errors;

import java.util.NoSuchElementException;

public class NoDataFoundException extends NoSuchElementException {
    private static final long serialVersionUID = -123456781L;

    public NoDataFoundException(String message)
    {
        super(message);
    }
}
