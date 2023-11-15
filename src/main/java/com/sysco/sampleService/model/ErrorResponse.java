package com.sysco.sampleService.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private int statusCode;
    private String message;

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
