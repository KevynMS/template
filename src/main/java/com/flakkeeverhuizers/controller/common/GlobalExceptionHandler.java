package com.flakkeeverhuizers.controller.common;

import com.flakkeeverhuizers.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResponseEntity<GlobalErrorResponse> handleException(ResponseStatusException e) {
        log.warn("ResponseStatusException: {}", e.getMessage());
        log.debug("ResponseStatusException", e);
        return new ResponseEntity<>(new GlobalErrorResponse(e.getClass().getSimpleName(), e.getReason()), e.getStatus());
    }

    @ExceptionHandler(value = {WebExchangeBindException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<GlobalErrorResponse> handleException(WebExchangeBindException e) {
        log.warn("Bad request: {} : {}", e.getClass(), e.getMessage());
        log.debug("Bad request stacktrace", e);
        return new ResponseEntity<>(new GlobalErrorResponse(e.getClass().getSimpleName(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {UserNotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<GlobalErrorResponse> handleException(UserNotFoundException e) {
        log.warn("Not found: {} : {}", e.getClass(), e.getMessage());
        log.debug("Not found", e);
        return new ResponseEntity<>(new GlobalErrorResponse(e.getClass().getSimpleName(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<GlobalErrorResponse> handleException(AuthenticationException e) {
        log.warn("Bad request: {} : {}", e.getClass(), e.getMessage());
        log.debug("Bad request", e);
        return new ResponseEntity<>(new GlobalErrorResponse(e.getClass().getSimpleName(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {CSVImportException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<GlobalErrorResponse> handleException(CSVImportException e) {
        log.warn("Bad request: {} : {}", e.getClass(), e.getMessage());
        log.debug("Bad request", e);
        return new ResponseEntity<>(new GlobalErrorResponse(e.getClass().getSimpleName(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {SignInException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<GlobalErrorResponse> handleException(SignInException e) {
        log.warn("Bad request: {} : {}", e.getClass(), e.getMessage());
        log.debug("Bad request", e);
        return new ResponseEntity<>(new GlobalErrorResponse(e.getClass().getSimpleName(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RecordNotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<GlobalErrorResponse> handleException(RecordNotFoundException e) {
        log.warn("Not found: {} : {}", e.getClass(), e.getMessage());
        log.debug("Not found", e);
        return new ResponseEntity<>(new GlobalErrorResponse(e.getClass().getSimpleName(), e.getMessage()), HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<GlobalErrorResponse> handleException(Exception e) {
        log.warn("Bad request: {} : {}", e.getClass(), e.getMessage());
        log.debug("Bad request stacktrace", e);
        return new ResponseEntity<>(new GlobalErrorResponse(e.getClass().getSimpleName(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<GlobalErrorResponse> handleThrowable(Throwable e) {
        log.error("Internal error", e);
        return new ResponseEntity<>(new GlobalErrorResponse(e.getClass().getSimpleName(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
