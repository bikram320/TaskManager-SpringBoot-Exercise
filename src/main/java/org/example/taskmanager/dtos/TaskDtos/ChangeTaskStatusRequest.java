package org.example.taskmanager.dtos.TaskDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.taskmanager.entities.StatusEnum;

@Data
public class ChangeTaskStatusRequest {

    @NotNull(message = "Previous status must be provided")
    private StatusEnum previousStatus;

    @NotNull(message = "New status must be provided")
    private StatusEnum newStatus;
}
