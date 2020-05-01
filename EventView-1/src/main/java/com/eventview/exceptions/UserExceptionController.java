package com.eventview.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = UserExistsException.class)
    public ResponseEntity<Object> userExistsException(UserExistsException exception) {
        return new ResponseEntity<>("User Exists", HttpStatus.CONFLICT);
    }
}
