package org.example.taskmanager.mapper;

import org.example.taskmanager.dtos.CreateUserRequest;
import org.example.taskmanager.dtos.UserDto;
import org.example.taskmanager.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User toUser(CreateUserRequest request);
}
