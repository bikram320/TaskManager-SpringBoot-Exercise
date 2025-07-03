package org.example.taskmanager.mapper;

import org.example.taskmanager.dtos.TaskDtos.TaskDto;
import org.example.taskmanager.entities.Task;
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
}
