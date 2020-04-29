package com.eventview.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EventExceptionController {
    @ExceptionHandler(value = EventNotFoundException.class)
    public ResponseEntity<Object> exception(EventNotFoundException exception) {
        return new ResponseEntity<>("Event Not Found", HttpStatus.NOT_FOUND);
    }
}
