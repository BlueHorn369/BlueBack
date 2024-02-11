package com.horn.blue.controllers;

import com.horn.blue.entities.Users;
import com.horn.blue.serviceinterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void registerUser(@RequestBody Users user) {
        userService.registerUser(user);
    }

    @GetMapping("/list")
    public List<Users> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("/search")
    public List<Users> searchUsers(@RequestParam String userName, @RequestParam String userLastName) {
        return userService.searchUsersByNameAndLastName(userName, userLastName);
    }

    @PutMapping("/update/{userId}")
    public void updateUserData(@PathVariable int userId, @RequestBody Users updatedUser) {
        userService.updateUserData(userId, updatedUser);
    }

    @PutMapping("/update/{userName}/email-password")
    public void updateEmailAndPassword(
            @PathVariable String userName,
            @RequestParam String newEmail,
            @RequestParam String newPassword
    ) {
        userService.updateEmailAndPassword(userName, newEmail, newPassword);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }
}