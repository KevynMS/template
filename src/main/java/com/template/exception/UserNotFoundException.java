package com.template.exception;

public class UserNotFoundException extends NotFoundResponseException{

    public UserNotFoundException(String message) {
        super(message);
    }

}
