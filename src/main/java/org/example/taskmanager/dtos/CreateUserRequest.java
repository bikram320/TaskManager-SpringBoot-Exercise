package org.example.taskmanager.dtos;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
     private String password;
   private String email;
}
