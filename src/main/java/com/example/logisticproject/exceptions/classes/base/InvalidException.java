package com.example.logisticproject.exceptions.classes.base;


public abstract class InvalidException extends RuntimeException {
    public InvalidException(String message) {
        super(message);
    }
}
