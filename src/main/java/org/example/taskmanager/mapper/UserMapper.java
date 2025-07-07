package org.example.taskmanager.mapper;

import org.example.taskmanager.dtos.UserDtos.RegisterUserRequest;
import org.example.taskmanager.dtos.UserDtos.UpdateUserRequest;
import org.example.taskmanager.dtos.UserDtos.UserDto;
import org.example.taskmanager.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);


    User toUser(RegisterUserRequest request);

    void toUpdateUser(UpdateUserRequest request , @MappingTarget User user);
}
