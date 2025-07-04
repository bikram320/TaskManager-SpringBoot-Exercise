package org.example.taskmanager.dtos.TaskDtos;

import lombok.Data;
import org.example.taskmanager.entities.StatusEnum;

@Data
public class CreateTaskRequest {
    private String name;
    private String description;
    private StatusEnum status;
    private Long userId;
}
