package com.template.exception;

public class SignInException extends BadRequestResponseException{

    public SignInException(String message) {
        super(message);
    }
}
