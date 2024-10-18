package com.example.logisticproject.exceptions.classes.base;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
