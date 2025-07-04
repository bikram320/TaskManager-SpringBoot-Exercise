package org.example.taskmanager.services;

import lombok.AllArgsConstructor;
import org.example.taskmanager.dtos.TaskDtos.ChangeTaskStatusRequest;
import org.example.taskmanager.dtos.TaskDtos.CreateTaskRequest;
import org.example.taskmanager.dtos.TaskDtos.TaskDto;
import org.example.taskmanager.entities.Task;
import org.example.taskmanager.mapper.TaskMapper;
import org.example.taskmanager.repositories.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public List<TaskDto> getTasksByUserId(long userId) {
        List<Task> tasks = taskRepository.findTasksByUserId(userId);
        return tasks.stream()
                .map(taskMapper::toTaskDto)
                .toList();
    }
    public TaskDto createTask(CreateTaskRequest request) {
        var newTask = taskMapper.toTask(request);
        taskRepository.save(newTask);

        return taskMapper.toTaskDto(newTask);
    }
    public ResponseEntity<Void> updateTaskStatus(long taskId, ChangeTaskStatusRequest newStatus) {
        var task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        if(!task.getStatus().equals(newStatus.getPreviousStatus())){
            return ResponseEntity.badRequest().build();
        }
        task.setStatus(newStatus.getNewStatus());
        taskRepository.save(task);
        return ResponseEntity.noContent().build();
    }
}
