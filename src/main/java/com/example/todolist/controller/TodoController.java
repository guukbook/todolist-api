package com.example.todolist.controller;

import com.example.todolist.exception.ResourceNotFoundException;
import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo", description = "Todo management APIs")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    @Operation(summary = "Get all todos", description = "Retrieve a list of all todo items")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Todo.class)))
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Create a new todo", description = "Create a new todo item")
    @ApiResponse(responseCode = "201", description = "Todo created",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Todo.class)))
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo savedTodo = todoRepository.save(todo);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a todo by ID", description = "Retrieve a todo item by its ID")
    @ApiResponse(responseCode = "200", description = "Found the todo",
                 content = @Content(mediaType = "application/json", 
                 schema = @Schema(implementation = Todo.class)))
    @ApiResponse(responseCode = "404", description = "Todo not found")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found for this id :: " + id));
        return ResponseEntity.ok().body(todo);
    } 
}    