package com.shubhranka.coderhack.controller;

import com.shubhranka.coderhack.entity.User;
import com.shubhranka.coderhack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/register")
    public String createUser(@RequestBody User user) {
        return userService.createUser(user).getUserId();
    }

    @GetMapping
    public String getUser() {
        return "Hi;";
    }
}
