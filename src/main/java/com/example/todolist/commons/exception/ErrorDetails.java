package com.example.todolist.commons.exception;

import lombok.Getter;

import java.util.Date;
@Getter
public class ErrorDetails extends RuntimeException {

    private final String message;

    private final String details;
    private final Date timestamp;

    public ErrorDetails(Date timestamp, String message, String details) {
        super(message);
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}