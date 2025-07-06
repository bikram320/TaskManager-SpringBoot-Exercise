package org.example.taskmanager.dtos.UserDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Data
public class UpdateUserRequest {

    @NotBlank(message = "name should be provided")
    @Size(min = 2, max = 50 , message ="name should be at least of 2 characters")
    private String name;

    @NotBlank(message = "email should be provided")
    @Email(message = "email should be valid")
    private String email;
}
