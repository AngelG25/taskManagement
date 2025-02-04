package com.portfolio.api.exceptions;

public class TaskNotFoundException extends RuntimeException {
    // TODO poner que devuelva un 404 en vez de un 500
    public TaskNotFoundException() {
        super();
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}
