package org.example.taskmanager.dtos.UserDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {

    @NotBlank(message = "name should be provided")
    @Size(min = 2, max = 50 , message ="name should be at least of 2 characters")
    private String name;

    @NotBlank(message = "password should be provided")
    @Size(min = 6, max = 100 , message = "password should be at least of 6 characters")
    private String password;

    @NotBlank(message = "email should be provided")
    @Email(message = "email should be valid")
   private String email;
}
