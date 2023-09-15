package com.palo.it.model.exception;

public class ApplicationException extends RuntimeException {

    public ApplicationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
