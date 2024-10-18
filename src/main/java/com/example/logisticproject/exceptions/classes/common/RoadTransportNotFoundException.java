package com.example.logisticproject.exceptions.classes.common;

import com.example.logisticproject.exceptions.classes.base.NotFoundException;

public class RoadTransportNotFoundException extends NotFoundException {
    public RoadTransportNotFoundException() {
        super("Road Transport Not Found");
    }
}
