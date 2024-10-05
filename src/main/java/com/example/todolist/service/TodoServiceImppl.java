package com.example.todolist.service;

import com.example.todolist.dto.TodoDTO;
import com.example.todolist.entity.TaskEntity;
import com.example.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class TodoServiceImppl implements TodoService {
    private final TaskRepository taskRepository;

    @Override
    public Page<TaskEntity> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    
    }
    
    @Override
    public TaskEntity getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public Long createTask(TodoDTO todoDTO) {
        TaskEntity task = new TaskEntity();
        task.setTitle(todoDTO.getTitle());
        task.setCompleted(todoDTO.isCompleted());
        return taskRepository.save(task).getId();
    }
    @Override 
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);

    }
}
