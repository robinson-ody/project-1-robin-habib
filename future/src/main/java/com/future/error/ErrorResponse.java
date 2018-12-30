package com.future.error;

public class ErrorResponse {
    private final String error;
    private final String errorDescription;


    public ErrorResponse(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }
}
