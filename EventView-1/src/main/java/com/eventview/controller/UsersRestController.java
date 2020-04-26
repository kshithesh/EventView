package com.eventview.controller;

import com.eventview.model.Users;
import com.eventview.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

    @GetMapping(path = "/user/{userid}")
    public ResponseEntity<Users> findByUserId(@PathVariable("userid") Integer userid) {
        log.info("getting user by userid{}", userid);
        Users users = userService.findByUserId(userid);

        if (users == null) {
            log.info("user with the id{} doesn't exist",userid);
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Users>(users, HttpStatus.OK);
    }

    @PostMapping(path = "/user")
    public ResponseEntity<Void>
    createUser(@RequestBody Users user) {
        log.info("creating new user:{}", user);

        if (userService.exists(user)) {
            log.info("user with same id " + user.getUserid() + " exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        userService.createUser(user);
        log.info("user created");
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    @PostMapping(path = "/user/{userid}")
    public ResponseEntity<Void>
    updateUser(@PathVariable Integer userid, @RequestBody Users users) {
        log.info("updating user:{}", users);
        Users users1 = userService.findByUserId(userid);

        if (users1 == null) {
            log.info("user with id{} doesn't exist", userid);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        users1.setUserid(users.getUserid());
        users1.setFname(users.getFname());
        users1.setLname(users.getLname());
        users1.setPhone(users.getPhone());
        users1.setEmail(users.getEmail());

        userService.updateUser(users);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/{userid}")
    public ResponseEntity<Void>
    deleteUser(@PathVariable Integer userid) {
        log.info("deleting user with id{}:", userid);
        Users users = userService.findByUserId(userid);

        if (users == null) {
            log.info("user with id{} doesn't exist", userid);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(userid);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
