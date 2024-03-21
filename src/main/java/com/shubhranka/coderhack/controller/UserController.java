package com.shubhranka.coderhack.controller;

import com.shubhranka.coderhack.dto.CreateUserDto;
import com.shubhranka.coderhack.dto.UpdateUserDto;
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
    public String createUser(@RequestBody CreateUserDto user) {
        User newUser = new User(user.getUserId(), user.getUserName());
        return "User with ID " + userService.createUser(newUser).getUserId() + " created successfully";
    }

    @PutMapping
    public String updateUser(@RequestBody UpdateUserDto user) {
        return "Scores given to  ID " + userService.updateUser(user).getUserId() + " updated successfully";
    }

    @GetMapping
    public String getUser() {
        return "Hi;";
    }
}
