package com.example.todolist.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "to_do")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "ID",
            defaultValue = "1",
            example = "1")
    private Long id;

    @Schema(description = "Заголовок",
            defaultValue = "Задание номер 1",
            example = "Задание номер 1")
    @Column(name = "title", nullable = false)
    private String title;

    @Schema(description = "Статус задачи",
            defaultValue = "false",
            example = "false")
    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted;

}