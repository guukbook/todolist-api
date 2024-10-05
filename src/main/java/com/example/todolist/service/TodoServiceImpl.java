package com.example.todolist.service;

import com.example.todolist.dto.TodoDTO;
import com.example.todolist.entity.StateEntity;
import com.example.todolist.entity.TaskEntity;
import com.example.todolist.entity.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
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
            StateEntity state = new StateEntity();
            state.setCompleted(false);
            task.setState(Set.of(state));

            return taskRepository.save(task).getId();

    }

    @Override
    @Transactional
    public void updateTask(Long id, TodoDTO todoDTO) {

        taskRepository.findById(id).ifPresent(taskEntity -> {

            Set<StateEntity> sets = new HashSet<>();
            sets.addAll(taskEntity.getState());

            StateEntity state = new StateEntity();
            state.setCompleted(todoDTO.isCompleted());
            state.setComment(todoDTO.getComment());
            sets.add(state);
            taskEntity.setState(sets);

        });

    }


    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);

    }
}
