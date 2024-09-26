package com.example.logisticproject.contoller.exception;

public class RegionNameAlreadyExistsException extends AlreadyExistsException{
    public RegionNameAlreadyExistsException(String name) {
        super("Region already exists with name: "+name);
    }
}
