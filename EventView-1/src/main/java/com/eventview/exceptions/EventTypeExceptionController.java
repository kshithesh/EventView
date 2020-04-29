package com.eventview.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EventTypeExceptionController {
    @ExceptionHandler(value = EventTypeNotFoundException.class)
    public ResponseEntity<Object> exception(EventTypeNotFoundException exception) {
        return new ResponseEntity<>("EventType Not Found", HttpStatus.NOT_FOUND);
    }
}
