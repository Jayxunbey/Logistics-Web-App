package com.example.logisticproject.exceptions.classes.common;

import com.example.logisticproject.exceptions.classes.base.NotFoundException;

public class AttachmentNotFoundException extends NotFoundException {
    public AttachmentNotFoundException(String message) {
        super(message);
    }
}
