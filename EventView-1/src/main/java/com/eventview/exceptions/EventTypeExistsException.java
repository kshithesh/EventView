package com.eventview.exceptions;

public class EventTypeExistsException extends RuntimeException{
    public EventTypeExistsException(String message) {
        super(message);
    }

    public EventTypeExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventTypeExistsException(Throwable cause) {
        super(cause);
    }
}
