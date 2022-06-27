package com.example.lib.config.exception;

public class ResourceNotFoundException extends RuntimeException {
    private String message;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
