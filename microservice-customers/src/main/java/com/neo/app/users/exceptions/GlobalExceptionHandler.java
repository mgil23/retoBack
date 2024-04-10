package com.neo.app.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorExceptionResponse> handleResourceNotFoundException(CustomerNotFoundException ex, WebRequest request) {
        ErrorExceptionResponse errorExceptionResponse = new ErrorExceptionResponse(HttpStatus.NOT_FOUND.value(), List.of(ex.getMessage()), getTimeFormatted());
        return new ResponseEntity<>(errorExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorExceptionResponse> handleException(Exception ex, WebRequest request) {
        ErrorExceptionResponse errorExceptionResponse = new ErrorExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), List.of(ex.getMessage()), getTimeFormatted());
        return new ResponseEntity<>(errorExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }
     
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        List<String> errorMessages = errors.entrySet().stream().map(entry -> entry.getKey() + ": " + entry.getValue()).collect(Collectors.toList());
        ErrorExceptionResponse errorExceptionResponse = new ErrorExceptionResponse(HttpStatus.BAD_REQUEST.value(), errorMessages, getTimeFormatted());
        return new ResponseEntity<>(errorExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        ErrorExceptionResponse errorExceptionResponse = new ErrorExceptionResponse(HttpStatus.BAD_REQUEST.value(), List.of(ex.getMessage()), getTimeFormatted());
        return new ResponseEntity<>(errorExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ErrorExceptionResponse errorExceptionResponse = new ErrorExceptionResponse(HttpStatus.BAD_REQUEST.value(), List.of(ex.getMessage()), getTimeFormatted());
        return new ResponseEntity<>(errorExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private String getTimeFormatted() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.now().format(dateTimeFormatter);
    }





}
