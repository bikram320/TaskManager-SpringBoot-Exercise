package org.example.taskmanager.controller;

import lombok.AllArgsConstructor;
import org.example.taskmanager.dtos.TaskDtos.TaskDto;
import org.example.taskmanager.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
