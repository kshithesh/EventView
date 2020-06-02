package com.eventview.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class EventViewExceptionController {

    @ExceptionHandler(value = EventNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(EventNotFoundException eventNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(eventNotFoundException.getMessage());
        errorResponse.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = EventExistsException.class)
    public ResponseEntity<ErrorResponse> existException(EventExistsException eventExistsException){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setMessage(eventExistsException.getMessage());
        errorResponse.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException userNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(userNotFoundException.getMessage());
        errorResponse.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = UserExistsException.class)
    public ResponseEntity<ErrorResponse> userExistsException(UserExistsException userExistsException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(userExistsException.getMessage());
        errorResponse.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = EventTypeNotFoundException.class)
    public ResponseEntity<ErrorResponse> eventTypeNotFoundException(EventTypeNotFoundException eventTypeNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(eventTypeNotFoundException.getMessage());
        errorResponse.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = EventTypeExistsException.class)
    public ResponseEntity<ErrorResponse> eventTypeExistsException(EventTypeExistsException eventTypeExistsException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setMessage(eventTypeExistsException.getMessage());
        errorResponse.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
