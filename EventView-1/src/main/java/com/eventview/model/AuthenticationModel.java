package com.eventview.model;

public class AuthenticationModel {

    private String message;

    public AuthenticationModel(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "AuthenticationModel{" +
                "message='" + message + '\'' +
                '}';
    }
}
