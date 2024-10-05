package com.example.todolist.entity;

import com.example.todolist.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StateRepository extends JpaRepository<StateEntity, Long> {
    
}
