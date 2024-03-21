package com.shubhranka.coderhack.controller;

import com.shubhranka.coderhack.dto.CreateUserDto;
import com.shubhranka.coderhack.dto.UpdateUserDto;
import com.shubhranka.coderhack.entity.User;
import com.shubhranka.coderhack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody CreateUserDto user) {
        return "User with ID " + userService.createUser(user).getUserId() + " created successfully";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable String id, @RequestBody UpdateUserDto user) {
        return "Scores given to  ID " + userService.updateUser(id,user).getUserId() + " updated successfully";
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @DeleteMapping
    public String deleteUser(@RequestParam String id) {
        return "User with ID " + userService.deleteUser(id).getUserId() + " deleted successfully";
    }

    @GetMapping
    public ArrayList<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
