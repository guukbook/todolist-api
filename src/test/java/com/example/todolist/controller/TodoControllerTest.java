package com.example.todolist.controller;

import com.example.todolist.model.Todo;
import com.example.todolist.model.TodoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;


import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    void testGetAllTodos() throws Exception {
        when(todoRepository.findAll()).thenReturn(List.of(
            Todo.builder().title("Todo 1").build(),
                Todo.builder().title("Todo 2").build())
        );

        mockMvc.perform(get("/api/todos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].title", is("Todo 1")))
            .andExpect(jsonPath("$[1].title", is("Todo 2")));
    }

    @Test
   void testCreateTodo() throws Exception {
        Todo todo = new Todo();
        todo.setTitle("New Todo");
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        mockMvc.perform(post("/api/todos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(todo)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title", is("New Todo")));
    }

  
}