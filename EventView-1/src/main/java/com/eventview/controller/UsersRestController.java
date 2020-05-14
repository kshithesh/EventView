package com.eventview.controller;

import com.eventview.exceptions.UserExistsException;
import com.eventview.model.Users;
import com.eventview.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersRestController {

    private final Logger log = LoggerFactory.getLogger(UsersRestController.class);

    @Autowired
    private UserService userService;

    @GetMapping(path = "/user")
    public ResponseEntity<List<Users>> getAllUsers() {
        log.info("getting all users");
        List<Users> users = userService.getAllUsers();
        if (users == null || users.isEmpty()) {
            log.info("no users found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("users received");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Users> findByUserId(@PathVariable("userId") Integer userId) {
        log.info("getting user by userId{}", userId);
        Users users = userService.findByUserId(userId);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(path = "/user")
    public ResponseEntity<Void>
    createUser(@RequestBody Users user) {
        log.info("creating new user:{}", user);
        if (userService.exists(user)) throw new UserExistsException("User already exists");

        userService.createUser(user);
        log.info("user created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping(path = "/user/{userId}")
    public ResponseEntity<Void>
    updateUser(@PathVariable Integer userId, @RequestBody Users users) {
        log.info("updating user:{}", users);
        Users users1 = userService.findByUserId(userId);

        if (users1 != null) {
            users1.setUserId(users.getUserId());
            users1.setFName(users.getFName());
            users1.setLName(users.getLName());
            users1.setPhone(users.getPhone());
            users1.setEmail(users.getEmail());
        }

        userService.updateUser(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/{userId}")
    public ResponseEntity<Void>
    deleteUser(@PathVariable Integer userId) {
        log.info("deleting user with id{}:", userId);
        Users users = userService.findByUserId(userId);

        if (users != null) {
            userService.deleteUser(userId);
        }
        log.info("User with id{} deleted",userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
