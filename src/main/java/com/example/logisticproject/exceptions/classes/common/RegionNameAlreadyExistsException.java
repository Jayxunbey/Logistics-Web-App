package com.example.logisticproject.exceptions.classes.common;

import com.example.logisticproject.exceptions.classes.base.AlreadyExistsException;

public class RegionNameAlreadyExistsException extends AlreadyExistsException {
    public RegionNameAlreadyExistsException(String name) {
        super("Region already exists with name: "+name);
    }
}
