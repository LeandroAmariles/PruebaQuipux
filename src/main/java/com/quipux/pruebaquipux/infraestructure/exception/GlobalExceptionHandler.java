package com.quipux.pruebaquipux.infraestructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Calendar;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {


  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ErrorDetails> handleConflicException(Exception exception){
    ErrorDetails error = ErrorDetails.builder()
        .timestamp(Calendar.getInstance().getTime())
        .details(exception.getLocalizedMessage())
        .message(exception.getMessage())
        .build();

    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  @ExceptionHandler(CreateException.class)
  public ResponseEntity<ErrorDetails> handleCreateException(Exception exception){
    ErrorDetails error = ErrorDetails.builder()
        .timestamp(Calendar.getInstance().getTime())
        .details(exception.getLocalizedMessage())
        .message(exception.getMessage())
        .build();

    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }
}
