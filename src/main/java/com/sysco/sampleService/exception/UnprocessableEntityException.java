package com.sysco.sampleService.exception;

public class UnprocessableEntityException extends RuntimeException {

    public UnprocessableEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
