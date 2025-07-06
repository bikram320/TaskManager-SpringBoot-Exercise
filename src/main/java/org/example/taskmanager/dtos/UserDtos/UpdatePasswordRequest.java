package org.example.taskmanager.dtos.UserDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePasswordRequest {

    @NotBlank(message = "old password should be provided")
    private String oldPassword;

    @NotBlank(message = "new password should be provided")
    @Size(min = 6 , message = "Password must be at least of 6 characters")
    private String newPassword;
}
