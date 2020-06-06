package com.eventview.model;

public class EmailText {

    private String firstName;
    private String evenType;

    public EmailText(String firstName, String evenType) {
        this.firstName = firstName;
        this.evenType = evenType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEvenType() {
        return evenType;
    }

    public void setEvenType(String evenType) {
        this.evenType = evenType;
    }
}
