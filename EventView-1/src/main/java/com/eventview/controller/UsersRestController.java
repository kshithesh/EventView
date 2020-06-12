package com.eventview.controller;

import com.eventview.exceptions.UserExistsException;
import com.eventview.exceptions.UserNotFoundException;
import com.eventview.model.Users;
import com.eventview.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UsersRestController {

    private final Logger log = LoggerFactory.getLogger(UsersRestController.class);

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    @ApiOperation(value = "View a list of Users")
    public String getAllUsers(Model model) {
        List<Users> users = userService.getAllUsers();
        if (users == null || users.isEmpty()) throw new UserNotFoundException("No users found to retrieve");
        model.addAttribute("users",users);
        //return new ResponseEntity<>(users, HttpStatus.OK);
        return "users/viewusers";
    }

    @GetMapping(path = "/user/{userId}")
    @ApiOperation(value = "Search User by the UserID")
    public ResponseEntity<Users> findByUserId(@PathVariable("userId") Integer userId) {
        log.info("getting user by userId{}", userId);
        Users users = userService.findByUserId(userId);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping("/user/form")
    public String showform(Model model){
        model.addAttribute("command", new Users());
        return "users/userform";
    }

    @PostMapping(path = "/save/user")
    @ApiOperation(value = "Create an EventType by providing FirstName, LastName, Phone and E-mail")
    public String createUser(@Valid @ModelAttribute("users") Users user) {
        log.info("creating new user:{}", user);
        if (userService.exists(user)) throw new UserExistsException("User already exists");
        userService.createUser(user);
        log.info("user created");
        return "redirect:/users";
        //return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/edit/user/{userId}")
    public String editform(@PathVariable("userId") Integer userId, Model model){
        Users users = userService.findByUserId(userId);
        model.addAttribute("command", users);
        return "users/edituser";
    }

    @PostMapping(path = "/update/user/{userId}")
    @ApiOperation(value = "Update an User by providing UserID, FirstName, LastName, Phone and E-mail")
    public String updateUser(@Valid @ModelAttribute("users") Users users) {
        log.info("updating user:{}", users);
        /*
        Users users1 = userService.findByUserId(userId);
        if (users1 != null) {
            users1.setUserId(userId);
            users1.setFirstName(users.getFirstName());
            users1.setLastName(users.getLastName());
            users1.setPhone(users.getPhone());
            users1.setEmail(users.getEmail());
        }
         */
        userService.updateUser(users);
        return "redirect:/users";
        //return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/del/user/{userId}")
    @ApiOperation(value = "Delete User by providing the UserID")
    public String
    deleteUser(@PathVariable Integer userId) {
        log.debug("deleting user with id{}:", userId);
        Users users = userService.findByUserId(userId);

        if (users != null) {
            userService.deleteUser(userId);
        } else {
            throw new UserNotFoundException("No User Found to Delete");
        }
        log.info("User with id{} deleted", userId);
        return "redirect:/users";
        //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
