package org.example.taskmanager.dtos.TaskDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.taskmanager.entities.StatusEnum;

@Data
public class CreateTaskRequest {

    @NotBlank(message = "Task Name must be Provided")
    @Size(min = 2,max = 40 , message = "Task name must be between 2 to 40 characters")
    private String name;

    @NotBlank(message = "Description must be Provided")
    @Size(min = 8 , max = 255 , message = "Description must be at least 8 characters")
    private String description;

    @NotNull(message = "status must be Provided")
    private StatusEnum status;

    @NotNull(message = "User's Id must be Provided")
    private Long userId;
}
