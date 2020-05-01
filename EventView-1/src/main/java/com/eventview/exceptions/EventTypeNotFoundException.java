package com.eventview.exceptions;

public class EventTypeNotFoundException extends RuntimeException{
    public EventTypeNotFoundException(String message) {
        super(message);
    }

    public EventTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventTypeNotFoundException(Throwable cause) {
        super(cause);
    }
}
