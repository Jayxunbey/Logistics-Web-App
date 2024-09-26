package com.example.logisticproject.exceptions.classes.base;


public abstract class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
