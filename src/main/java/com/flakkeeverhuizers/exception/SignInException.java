package com.flakkeeverhuizers.exception;

public class SignInException extends BadRequestResponseException{

    public SignInException(String message) {
        super(message);
    }
}
