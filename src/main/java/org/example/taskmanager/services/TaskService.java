package org.example.taskmanager.services;

import lombok.AllArgsConstructor;
import org.example.taskmanager.dtos.TaskDtos.ChangeTaskStatusRequest;
import org.example.taskmanager.dtos.TaskDtos.CreateTaskRequest;
import org.example.taskmanager.dtos.TaskDtos.TaskDto;
import org.example.taskmanager.entities.Task;
import org.example.taskmanager.exceptions.DuplicateDataException;
import org.example.taskmanager.exceptions.ResourceNotFoundException;
import org.example.taskmanager.mapper.TaskMapper;
import org.example.taskmanager.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;
    private TaskMapper taskMapper;

    public List<TaskDto> getAllTasks() {
        return taskRepository
                .findAll()
                .stream()
                .map(task -> taskMapper.toTaskDto(task))
                .toList();
    }

    public List<TaskDto> getTasksByUserId(long userId) throws Exception {
        List<Task> tasks = taskRepository.findTasksByUserId(userId);
        if (tasks.isEmpty()) {
            throw  new ResourceNotFoundException("task not found associated with given user Id");
        }
        return tasks.stream()
                .map(taskMapper::toTaskDto)
                .toList();
    }
    public TaskDto createTask(CreateTaskRequest request) {
        var newTask = taskMapper.toTask(request);
        taskRepository.save(newTask);
        return taskMapper.toTaskDto(newTask);
    }
    public void updateTaskStatus(long taskId, ChangeTaskStatusRequest newStatus) throws Exception {
        var task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            throw new ResourceNotFoundException("Task not found with associated Task Id");
        }
        //validating if the previous status didn't match
        if(!task.getStatus().equals(newStatus.getPreviousStatus())){
            throw new InputMismatchException("Previous status mismatch");
        }

        //validating if the status is same as old
        if(newStatus.getPreviousStatus().equals(newStatus.getNewStatus())){
            throw new DuplicateDataException("new Status is same as previous Status");
        }
        task.setStatus(newStatus.getNewStatus());
        taskRepository.save(task);
    }
}
