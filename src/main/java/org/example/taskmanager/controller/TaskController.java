package org.example.taskmanager.controller;

import jakarta.validation.Valid;
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
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        var tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TaskDto>> getTasksByUserId(@PathVariable long id) throws Exception {
        List<TaskDto> taskDtos = taskService.getTasksByUserId(id);
        return new ResponseEntity<>(taskDtos, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<TaskDto> createTask( @Valid @RequestBody CreateTaskRequest request) {
        var taskDto =taskService.createTask(request);
        return new ResponseEntity<>(taskDto, HttpStatus.CREATED);
    }
    @PostMapping("/{id}/change-status")
    public ResponseEntity<?> changeTaskStatus(
            @PathVariable long id, @Valid @RequestBody ChangeTaskStatusRequest newStatus) throws Exception {
         taskService.updateTaskStatus(id,newStatus);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
