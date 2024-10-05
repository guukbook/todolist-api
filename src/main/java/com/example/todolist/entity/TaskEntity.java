package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "tasks")
@Data

public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.INDENTITY)
    private Long id;

    private String title;

    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private StateEntity state;
    
}
