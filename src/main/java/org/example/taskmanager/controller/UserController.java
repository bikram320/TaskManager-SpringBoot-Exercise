package org.example.taskmanager.controller;

import lombok.AllArgsConstructor;
import org.example.taskmanager.dtos.UserDtos.CreateUserRequest;
import org.example.taskmanager.dtos.UserDtos.UpdatePasswordRequest;
import org.example.taskmanager.dtos.UserDtos.UpdateUserRequest;
import org.example.taskmanager.dtos.UserDtos.UserDto;
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

    @PostMapping("/change-password/{id}")
    public ResponseEntity<Void> changePassword(
            @PathVariable long id , @RequestBody UpdatePasswordRequest request) {
        return userService.updatePassword(id, request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
