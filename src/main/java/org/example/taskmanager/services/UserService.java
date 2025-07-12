package org.example.taskmanager.services;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.taskmanager.dtos.UserDtos.RegisterUserRequest;
import org.example.taskmanager.dtos.UserDtos.UpdatePasswordRequest;
import org.example.taskmanager.dtos.UserDtos.UpdateUserRequest;
import org.example.taskmanager.dtos.UserDtos.UserDto;
import org.example.taskmanager.exceptions.DuplicateDataException;
import org.example.taskmanager.exceptions.ResourceNotFoundException;
import org.example.taskmanager.mapper.UserMapper;
import org.example.taskmanager.repositories.UserRepository;
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
    public UserDto getUserById(Long id) throws ResourceNotFoundException {
        var user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("user with given Id not found"));
        return userMapper.toUserDto(user);
    }
    public UserDto registerUser(@Valid RegisterUserRequest request) {

        //validating email if it is already registered or not
        if (userRepository.existsUserByEmail(request.getEmail())){
            throw new DuplicateDataException("Email address already in use");
        }
        var newUser = userMapper.toUser(request);
        userRepository.save(newUser);

        return userMapper.toUserDto(newUser);
    }
    public UserDto updateUser(@Valid UpdateUserRequest request, Long id)  throws Exception {
        var user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User Not Found of given Id"));

        //validating email if it is same as old email
        if (user.getEmail().equals(request.getEmail())){
            throw new DuplicateDataException("Email is same as old email .");
        }

        //validating email if it is already registered or not
        if (userRepository.existsUserByEmail(request.getEmail())){
            throw new DuplicateDataException("Email is Already Registered.");
        }
        userMapper.toUpdateUser(request, user);
        userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    public void updatePassword(long id , @Valid UpdatePasswordRequest request) throws Exception {
        var user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("user not found with given Id"));
        //validating if user input correct old password or not
        if (!user.getPassword().equals(request.getOldPassword())){
            throw new DuplicateDataException("Old password doesn't match");
        }

        //validating if password is same as old password
        if (user.getPassword().equals(request.getNewPassword())){
            throw new DuplicateDataException("New password is same as old password");
        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
    }
    public void deleteUser(Long id) throws ResourceNotFoundException {
        var user = userRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("User not found with given Id"));
        userRepository.delete(user);
    }
}
