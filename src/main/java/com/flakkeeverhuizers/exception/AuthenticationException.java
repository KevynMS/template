package com.flakkeeverhuizers.exception;

public class AuthenticationException extends BadRequestResponseException{

    public AuthenticationException(String message) {
        super(message);
    }
}
