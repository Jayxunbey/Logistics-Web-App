package com.example.logisticproject.contoller.exception;

import lombok.AllArgsConstructor;


public abstract class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
