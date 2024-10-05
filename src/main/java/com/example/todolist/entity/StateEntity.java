package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "states")
@Data

public class StateEntity {
    @Id@GeneratedValue(strategy = GenerationType.INDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    private List<TaskEntity> task;
    

}
