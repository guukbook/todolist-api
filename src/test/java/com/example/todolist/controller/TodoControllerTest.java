package com.example.todolist.controller;

import com.example.todolist.dto.TodoDTO;
import com.example.todolist.entity.TaskEntity;
import com.example.todolist.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TodoService todoService;

    @Test
    void testGetAllTasks() throws Exception {
        TaskEntity task = new TaskEntity();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setCompleted(false);

        Page<TaskEntity> page = new PageImpl<>(List.of(task));

        when(todoService.getAllTasks(any())).thenReturn(page);
      
        mockMvc.perform(get("/api/v1/todos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].id").value(1))
            .andExpect(jsonPath("$.content[0].title").value("Test Task"))
            .andExpect(jsonPath("$.content[0].completed").value(false));
    }

    @Test
   void testCreateTask() throws Exception {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setTitle("New Task");
        todoDTO.setCompleted(false);

        when(todoService.createTask(any(TodoDTO.class))).thenReturn(1L);

        mockMvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todoDTO)))
                 .andExpect(status().isCreated())
                 .andExpect(content().string("1"));
                
    
    }

  
}