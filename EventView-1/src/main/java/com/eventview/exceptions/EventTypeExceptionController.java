package com.eventview.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EventTypeExceptionController {
    @ExceptionHandler(value = EventTypeNotFoundException.class)
    public ResponseEntity<Object> eventTypeNotFoundException(EventTypeNotFoundException eventTypeNotFoundException) {
        return new ResponseEntity<>("EventType Not Found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = EventExistsException.class)
    public ResponseEntity<Object> eventExistsException(EventExistsException eventExistsException) {
        return new ResponseEntity<>("EventType Exists", HttpStatus.CONFLICT);
    }
}
