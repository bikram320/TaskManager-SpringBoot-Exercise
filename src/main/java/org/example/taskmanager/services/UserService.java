package org.example.taskmanager.services;

import lombok.AllArgsConstructor;
import org.example.taskmanager.dtos.UserDtos.CreateUserRequest;
import org.example.taskmanager.dtos.UserDtos.UpdatePasswordRequest;
import org.example.taskmanager.dtos.UserDtos.UpdateUserRequest;
import org.example.taskmanager.dtos.UserDtos.UserDto;
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
    public ResponseEntity<UserDto> updateUser(UpdateUserRequest request, Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userMapper.toUpdateUser(request, user);
        userRepository.save(user);
        UserDto userDto = userMapper.toUserDto(user);
        return ResponseEntity.ok(userDto);
    }

    public ResponseEntity<Void> UpdatePassword(long id , UpdatePasswordRequest request) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        if (!user.getPassword().equals(request.getOldPassword())){
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }
}
