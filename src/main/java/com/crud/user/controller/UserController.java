package com.crud.user.controller;

import com.crud.user.model.User;
import com.crud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody User user) {
        User addedUser = userService.create(user);
        return ResponseEntity.status(CREATED).body(addedUser);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.status(OK).body(user);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<?> update(@RequestBody User user) {
        User updatedUser = userService.update(user);
        return ResponseEntity.status(OK).body(updatedUser);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable int userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.status(OK).build();
    }
}
