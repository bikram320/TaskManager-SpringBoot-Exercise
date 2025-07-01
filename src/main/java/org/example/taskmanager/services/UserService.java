package org.example.taskmanager.services;

import lombok.AllArgsConstructor;
import org.example.taskmanager.dtos.CreateUserRequest;
import org.example.taskmanager.dtos.UserDto;
import org.example.taskmanager.mapper.UserMapper;
import org.example.taskmanager.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ResponseEntity<UserDto> createUser(CreateUserRequest request) {
        var newUser = userMapper.toUser(request);
        userRepository.save(newUser);

        UserDto userDto = userMapper.toUserDto(newUser);
        return ResponseEntity.ok(userDto);
    }
}
