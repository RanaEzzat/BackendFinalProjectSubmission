package io.javabrains.springbootquickstart.courseapidata.errors;

import java.util.NoSuchElementException;

public class UserDoesNotExistException extends NoSuchElementException {
    private static final long serialVersionUID = -123456786L;

    public UserDoesNotExistException(String message)
    {
        super(message);
    }
}
