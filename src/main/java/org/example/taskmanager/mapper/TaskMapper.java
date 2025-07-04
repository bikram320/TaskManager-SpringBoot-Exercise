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

    @Mapping(source = "id",target = "id")
    @Mapping(source = "name",target = "name")
    @Mapping(source ="description",target = "description")
    @Mapping(source = "status",target = "status")
    @Mapping(source = "user.id",target = "userId")
    TaskDto toTaskDto(Task task);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "userId" , target = "user.id")
    Task toTask(CreateTaskRequest request);

}
