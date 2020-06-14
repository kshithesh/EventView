package com.eventview.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Users {

    @ApiModelProperty(notes = "The database generated UserID")
    private Integer userId;

    @Size(min = 3, message = "Enter valid first name with 3 or more characters")
    @NotNull(message = "FirstName cannot be empty")
    @ApiModelProperty(notes = "FirstName of the User")
    private String firstName;

    @NotNull(message = "LastName cannot be empty")
    @ApiModelProperty(notes = "LastName of the User")
    private String lastName;

    @Size(min = 10, message = "Enter valid phone number")
    @NotNull(message = "Phone Number cannot be empty")
    @ApiModelProperty(notes = "Phone number of the User")
    private String phone;

    @NotNull(message = "Email cannot be empty")
    @ApiModelProperty(notes = "E-Mail of the User")
    @Email
    private String email;

    public Users(Integer userId, String firstName, String lastName, String phone, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public Users() {

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEventTypes(EventTypes eventTypes) {
    }
}
