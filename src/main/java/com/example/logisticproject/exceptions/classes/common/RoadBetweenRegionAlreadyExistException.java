package com.example.logisticproject.exceptions.classes.common;

import com.example.logisticproject.exceptions.classes.base.AlreadyExistsException;

public class RoadBetweenRegionAlreadyExistException extends AlreadyExistsException {
    public RoadBetweenRegionAlreadyExistException() {
        super("Road Already Exists");
    }
}
