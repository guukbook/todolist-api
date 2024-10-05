package com.example.todolist.service;

import com.example.todolist.dto.TodoDTO;
import com.example.todolist.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoService {
    Page<TaskEntity> getAllTasks(Pageable pageable);
    TaskEntity getTaskById(Long id);
    Long createTask(TodoDTO todoDTO);
    void updateTask(Long id, TodoDTO todoDTO);
    void deleteTask(Long id);
    
}
