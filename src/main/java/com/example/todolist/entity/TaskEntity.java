package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Entity
@Table(name = "tasks")
@Data
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<StateEntity> state;
    
}
