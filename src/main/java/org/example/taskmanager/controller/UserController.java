package org.example.taskmanager.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.taskmanager.dtos.UserDtos.RegisterUserRequest;
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
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        return userService.registerUser(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserRequest(
            @PathVariable long id ,
            @Valid
            @RequestBody UpdateUserRequest request){
        return userService.updateUser(request,id);
    }

    @PostMapping("/change-password/{id}")
    public ResponseEntity<?> changePassword(
            @PathVariable long id , @Valid @RequestBody UpdatePasswordRequest request) {
        return userService.updatePassword(id, request);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
