package com.flakkeeverhuizers.exception;

public class RecordNotFoundException extends NotFoundResponseException{

    public RecordNotFoundException(String message) {
        super(message);
    }
}
