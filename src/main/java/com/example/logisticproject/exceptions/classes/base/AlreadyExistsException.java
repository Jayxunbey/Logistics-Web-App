package com.example.logisticproject.exceptions.classes.base;


public abstract class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
