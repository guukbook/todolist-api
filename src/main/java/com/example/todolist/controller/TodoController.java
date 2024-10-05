package com.example.todolist.controller;

import com.example.todolist.commons.exception.ResourceNotFoundException;
import com.example.todolist.model.Todo;
import com.example.todolist.model.TodoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.example.todolist.commons.Statics.TO_DO_CONTROLLER;

@RestController
@RequestMapping(TO_DO_CONTROLLER)
@Tag(name = "Todo", description = "Todo management APIs")
@RequiredArgsConstructor
public class TodoController {

    private final TodoRepository todoRepository;

    @GetMapping
    @Operation(summary = "Get all todos", description = "Retrieve a list of all todo items")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Todo.class)))
    public Page<Todo> getAllTodos(Pageable pageable) {
        return todoRepository.findAll(pageable);
    }

    @PostMapping
    @Operation(summary = "Create a new todo", description = "Create a new todo item")
    @ApiResponse(responseCode = "201", description = "Todo created",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Todo.class)))
    @ResponseStatus(HttpStatus.CREATED)
    public Long createTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo).getId();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a todo by ID", description = "Retrieve a todo item by its ID")
    @ApiResponse(responseCode = "200", description = "Found the todo",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Todo.class)))
    @ApiResponse(responseCode = "404", description = "Todo not found")
    @ResponseStatus(HttpStatus.OK)
    public Todo getTodoById(@Schema(defaultValue = "Передай ID нужной задачи") @PathVariable Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found for this id :: " + id));
    } 
}    