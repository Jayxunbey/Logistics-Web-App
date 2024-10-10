package com.example.logisticproject.exceptions.classes.common;

import com.example.logisticproject.exceptions.classes.base.AlreadyExistsException;

public class RoadTransportAlreadyExistsException extends AlreadyExistsException {
    public RoadTransportAlreadyExistsException(String message) {
        super(message);
    }
}
