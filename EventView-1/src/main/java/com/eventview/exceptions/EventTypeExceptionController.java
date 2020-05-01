package com.eventview.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class EventTypeExceptionController {
    @ExceptionHandler(value = EventTypeNotFoundException.class)
    public ResponseEntity<ErrorResponse> eventTypeNotFoundException(EventTypeNotFoundException etnf) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(etnf.getMessage());
        errorResponse.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = EventExistsException.class)
    public ResponseEntity<ErrorResponse> eventExistsException(EventExistsException ee) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setMessage(ee.getMessage());
        errorResponse.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
