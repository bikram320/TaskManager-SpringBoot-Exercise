package org.example.taskmanager.controller;

import lombok.AllArgsConstructor;
import org.example.taskmanager.dtos.TaskDtos.ChangeTaskStatusRequest;
import org.example.taskmanager.dtos.TaskDtos.CreateTaskRequest;
import org.example.taskmanager.dtos.TaskDtos.TaskDto;
import org.example.taskmanager.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<TaskDto>> getTasksByUserId(@PathVariable long id) {
        List<TaskDto> taskDtos = taskService.getTasksByUserId(id);
        if (taskDtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskDtos);
    }
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody CreateTaskRequest request) {
        var taskDto =taskService.createTask(request);
        return new ResponseEntity<>(taskDto, HttpStatus.CREATED);
    }
    @PostMapping("/{id}/change-status")
    public ResponseEntity<Void> changeTaskStatus(
            @PathVariable long id, @RequestBody ChangeTaskStatusRequest newStatus) {
        return taskService.updateTaskStatus(id,newStatus);
    }
}
