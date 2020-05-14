package com.eventview.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class EventViewExceptionController {
    @ExceptionHandler(value = EventNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(EventNotFoundException enf) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(enf.getMessage());
        errorResponse.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = EventExistsException.class)
    public ResponseEntity<ErrorResponse> existException(EventExistsException ee){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setMessage(ee.getMessage());
        errorResponse.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = UserExistsException.class)
    public ResponseEntity<ErrorResponse> userExistsException(UserExistsException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = EventTypeNotFoundException.class)
    public ResponseEntity<ErrorResponse> eventTypeNotFoundException(EventTypeNotFoundException etnf) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(etnf.getMessage());
        errorResponse.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = EventTypeExistsException.class)
    public ResponseEntity<ErrorResponse> eventTypeExistsException(EventTypeExistsException ee) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setMessage(ee.getMessage());
        errorResponse.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
