package com.eventview.controller;

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

    @GetMapping(path = "/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        log.info("getting all users");
        List<Users> users = userService.getAllUsers();
        if (users == null || users.isEmpty()) {
            log.info("no users found");
            return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
        }
        log.info("users received");
        return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/user/{user_id}")
    public ResponseEntity<Users> findByUserId(@PathVariable("user_id") Integer user_id) {
        log.info("getting user by user_id{}", user_id);
        Users users = userService.findByUserId(user_id);

        if (users == null) {
            log.info("user with the id{} doesn't exist",user_id);
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Users>(users, HttpStatus.OK);
    }

    @PostMapping(path = "/user")
    public ResponseEntity<Void>
    createUser(@RequestBody Users users) {
        log.info("creating new user:{}", users);

        /*
        if (userService.exists(users)) {
            log.info("user with same id " + users.getUser_id() + " exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
         */
        userService.createUser(users);
        log.info("user created");
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    @PostMapping(path = "/user/{user_id}")
    public ResponseEntity<Void>
    updateUser(@PathVariable Integer user_id, @RequestBody Users users) {
        log.info("updating user:{}", users);
        Users users1 = userService.findByUserId(user_id);

        if (users1 == null) {
            log.info("user with id{} doesn't exist", user_id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        users1.setUser_id(users.getUser_id());
        users1.setFirst_name(users.getFirst_name());
        users1.setLast_name(users.getLast_name());
        users1.setPhone(users.getPhone());
        users1.setEmail(users.getEmail());

        userService.updateUser(users);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/{user_id}")
    public ResponseEntity<Void>
    deleteUser(@PathVariable Integer user_id) {
        log.info("deleting user with id{}:", user_id);
        Users users = userService.findByUserId(user_id);

        if (users == null) {
            log.info("user with id{} doesn't exist", user_id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(user_id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
