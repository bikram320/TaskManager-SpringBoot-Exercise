package org.example.taskmanager.services;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.taskmanager.dtos.UserDtos.RegisterUserRequest;
import org.example.taskmanager.dtos.UserDtos.UpdatePasswordRequest;
import org.example.taskmanager.dtos.UserDtos.UpdateUserRequest;
import org.example.taskmanager.dtos.UserDtos.UserDto;
import org.example.taskmanager.mapper.UserMapper;
import org.example.taskmanager.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> userMapper.toUserDto(user))
                .toList();
    }
    public ResponseEntity<UserDto> getUserById(Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.toUserDto(user));
    }
    public ResponseEntity<?> registerUser(@Valid RegisterUserRequest request) {

        //validating email if it is already registered or not
        if (userRepository.existsUserByEmail(request.getEmail())){
            return ResponseEntity.badRequest().body(
                    Map.of("email","email is already registered")
            );
        }
        var newUser = userMapper.toUser(request);
        userRepository.save(newUser);

        UserDto userDto = userMapper.toUserDto(newUser);
        return ResponseEntity.ok(userDto);
    }
    public ResponseEntity<?> updateUser(@Valid UpdateUserRequest request, Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        //validating email if it is same as old email
        if (user.getEmail().equals(request.getEmail())){
            return ResponseEntity.badRequest().body(
                    Map.of("email","email is same as old email")
            );
        }

        //validating email if it is already registered or not
        if (userRepository.existsUserByEmail(request.getEmail())){
            return ResponseEntity.badRequest().body(
                    Map.of("email","email is already registered")
            );
        }
        userMapper.toUpdateUser(request, user);
        userRepository.save(user);
        UserDto userDto = userMapper.toUserDto(user);
        return ResponseEntity.ok(userDto);
    }

    public ResponseEntity<?> updatePassword(long id , @Valid UpdatePasswordRequest request) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        //validating if user input correct old password or not
        if (!user.getPassword().equals(request.getOldPassword())){
            return ResponseEntity.badRequest().body(
                    Map.of("oldPassword","old password is incorrect")
            );
        }

        //validating if password is same as old password
        if (user.getPassword().equals(request.getNewPassword())){
            return ResponseEntity.badRequest().body(
                    Map.of("Password","Password is same as old Password,try another password")
            );
        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }
    public void deleteUser(Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            ResponseEntity.notFound().build();
            return;
        }
        userRepository.delete(user);
    }
}
