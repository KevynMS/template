package com.template.exception;

public class RecordNotFoundException extends NotFoundResponseException{

    public RecordNotFoundException(String message) {
        super(message);
    }
}
