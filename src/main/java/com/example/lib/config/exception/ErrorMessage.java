package com.example.lib.config.exception;

import lombok.Data;

@Data
public class ErrorMessage {
    private int statusCode;

    private String message;

}
