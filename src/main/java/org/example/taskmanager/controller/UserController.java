package org.example.taskmanager.controller;

import lombok.AllArgsConstructor;
import org.example.taskmanager.dtos.CreateUserRequest;
import org.example.taskmanager.dtos.UpdateUserRequest;
import org.example.taskmanager.dtos.UserDto;
import org.example.taskmanager.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserRequest(
            @PathVariable long id ,
            @RequestBody UpdateUserRequest request){
        return userService.updateUser(request,id);
    }
}
