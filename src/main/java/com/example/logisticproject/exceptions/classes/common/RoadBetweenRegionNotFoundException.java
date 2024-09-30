package com.example.logisticproject.exceptions.classes.common;

import com.example.logisticproject.exceptions.classes.base.NotFoundException;

public class RoadBetweenRegionNotFoundException extends NotFoundException {
    public RoadBetweenRegionNotFoundException() {
        super("Road Between Region Not Found");
    }
}
