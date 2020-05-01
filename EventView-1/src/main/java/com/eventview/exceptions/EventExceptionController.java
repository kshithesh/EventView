package com.eventview.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EventExceptionController {
    @ExceptionHandler(value = EventNotFoundException.class)
    public ResponseEntity<Object> notFoundException(EventNotFoundException notFoundException) {
        return new ResponseEntity<>("Event Not Found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = EventExistsException.class)
    public ResponseEntity<Object> existException(EventExistsException existException){
        return new ResponseEntity<>("Event Exists", HttpStatus.CONFLICT);
    }
}
