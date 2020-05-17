package com.eventview.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @Column(name = "first_name")
    @NotNull
    private String fName;

    @Column(name = "last_name")
    @NotNull
    private String lName;

    @Column(name = "phone")
    @NotNull
    private String phone;

    @Column(name = "email")
    @NotNull
    private String email;

    public Users(Integer userId, String fName, String lName, String phone, String email) {
        this.userId = userId;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.email = email;
    }

    public Users() {

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userid) {
        this.userId = userid;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fname) {
        this.fName = fname;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lname) {
        this.lName = lname;
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
}
