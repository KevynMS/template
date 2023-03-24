package com.flakkeeverhuizers.exception;

public class BadRequestResponseException extends RuntimeException {

    public BadRequestResponseException(String message) {
        super(message);
    }
}
