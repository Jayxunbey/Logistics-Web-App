package com.example.logisticproject.exceptions.classes.common;

import com.example.logisticproject.exceptions.classes.base.NotFoundException;

public class TransportNotFoundException extends NotFoundException {
    public TransportNotFoundException(String message) {
        super(message);
    }
}
