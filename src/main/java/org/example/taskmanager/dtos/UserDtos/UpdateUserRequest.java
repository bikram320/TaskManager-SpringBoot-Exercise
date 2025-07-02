package org.example.taskmanager.dtos.UserDtos;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String name;
    private String email;
}
