package org.example.taskmanager.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException exception) {
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = (error instanceof FieldError) ? ((FieldError) error).getField() : error.getObjectName();
            errors.put(fieldName, error.getDefaultMessage());
        });
        return  ResponseEntity.badRequest().body(errors);
    }
}
