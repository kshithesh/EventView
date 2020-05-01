package com.eventview.exceptions;

public class EventExistsException extends RuntimeException{
    public EventExistsException(String message) {
        super(message);
    }

    public EventExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventExistsException(Throwable cause) {
        super(cause);
    }
}
