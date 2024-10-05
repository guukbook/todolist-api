package com.example.todolist.controller;

import com.example.todolist.dto.TodoDTO;
import com.example.todolist.entity.TaskEntity;
import com.example.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.example.todolist.commons.Statics.TO_DO_CONTROLLER;

@RestController
@RequestMapping(TO_DO_CONTROLLER)
@RequiredArgsConstructor

public class TodoController {
    
    private final TodoService todoService;

    @GetMapping
    
    public Page<TaskEntity> getAllTasks(Pageable pageable) {
        return todoService.getAllTasks(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createTask(@RequestBody TodoDTO todoDTO) {
        return todoService.createTask(todoDTO);
    }

    @GetMapping("/{id}")
    public TaskEntity getTaskById(@PathVariable Long id) {
        return todoService.getTaskById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        todoService.deleteTask(id);
    }
}
