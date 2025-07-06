package org.example.taskmanager.mapper;

import org.example.taskmanager.dtos.TaskDtos.CreateTaskRequest;
import org.example.taskmanager.dtos.TaskDtos.TaskDto;
import org.example.taskmanager.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {


    @Mapping(source = "user.id", target = "userId")
    TaskDto toTaskDto(Task task);

    @Mapping(source = "userId", target = "user.id")
    Task toTask(CreateTaskRequest request);

}
