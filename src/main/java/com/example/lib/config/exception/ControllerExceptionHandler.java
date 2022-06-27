package com.example.lib.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex) {
        ErrorMessage message = new ErrorMessage();
        message.setMessage(ex.getMessage());
        message.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(BindException ex) {
        ErrorMessage message = new ErrorMessage();
        message.setMessage(ex.getAllErrors().get(0).getDefaultMessage());
        message.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadRequestException.class)
    public String badRequestException(BadRequestException ex, RedirectAttributes flashSession) {
        flashSession.addFlashAttribute("failed", ex.getMessage());
        return "redirect:/book/borrow";
    }
}
