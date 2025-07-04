package org.example.taskmanager.dtos.TaskDtos;

import lombok.Data;
import org.example.taskmanager.entities.StatusEnum;

@Data
public class ChangeTaskStatusRequest {
    private StatusEnum previousStatus;
    private StatusEnum newStatus;
}
