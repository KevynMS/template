package com.flakkeeverhuizers.exception;

public class NotFoundResponseException extends RuntimeException{

    public NotFoundResponseException(String message) {
        super(message);
    }
}
