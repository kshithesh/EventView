package com.eventview.controller;

import com.eventview.model.Users;
import com.eventview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersRestController {

    private final UserService userService;

    @Autowired
    public UsersRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/users/{user_id}")
    public Users findByUserId(@PathVariable Integer user_id) {
        return userService.findByUserId(user_id);
    }

    @PostMapping(path = "/usercreate")
    public @ResponseBody
    Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }

    @PostMapping(path = "/useredit")
    public @ResponseBody Users updateUser(@RequestBody Users user) {
        return userService.updateUser(user);
    }

    @DeleteMapping(path = "/userdel")
    public @ResponseBody Users deleteRide(@RequestBody Users user) {
        return userService.deleteUser(user);
    }


}
