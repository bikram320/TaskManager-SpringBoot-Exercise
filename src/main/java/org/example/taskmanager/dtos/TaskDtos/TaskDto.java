package org.example.taskmanager.dtos.TaskDtos;

import lombok.Data;
import org.example.taskmanager.entities.StatusEnum;
import org.example.taskmanager.entities.User;

@Data
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private StatusEnum status;
    private Long userId;
}
