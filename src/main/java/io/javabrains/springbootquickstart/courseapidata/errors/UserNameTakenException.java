package io.javabrains.springbootquickstart.courseapidata.errors;

public class UserNameTakenException extends RuntimeException {
    private static final long serialVersionUID = -123456787L;

    public UserNameTakenException(String message)
    {
        super(message);
    }
}