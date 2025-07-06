package org.example.taskmanager.mapper;

import org.example.taskmanager.dtos.TaskDtos.CreateTaskRequest;
import org.example.taskmanager.dtos.TaskDtos.TaskDto;
import org.example.taskmanager.entities.Task;
import org.example.taskmanager.entities.User;
import org.example.taskmanager.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {


    TaskDto toTaskDto(Task task);


    Task toTask(CreateTaskRequest request);

}
