package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "states")
@Data
public class StateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private boolean completed;

    @CreationTimestamp
    @Column(name = "changed_at")
    private LocalDateTime changedAt;

    @ManyToOne
    private TaskEntity task;
    

}
