package com.template.exception;

public class NotFoundResponseException extends RuntimeException{

    public NotFoundResponseException(String message) {
        super(message);
    }
}
