package com.example.todolist.exception;

import java.util.Date;

public class ErrorDetails {
    @SuppressWarnings("unused")
    private String message;
    @SuppressWarnings("unused")
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        this.message = message;
        this.details = details;
    }

    // Геттеры
}