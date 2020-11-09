package io.javabrains.springbootquickstart.courseapidata.errors;

public class NonAdminAccessException extends RuntimeException{
    private static final long serialVersionUID = -123456782L;

    public NonAdminAccessException(String message)
    {
        super(message);
    }
}
