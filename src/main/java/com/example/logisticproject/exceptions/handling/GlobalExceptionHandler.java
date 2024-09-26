package com.example.logisticproject.exceptions.handling;

import com.example.logisticproject.exceptions.classes.base.AlreadyExistsException;
import com.example.logisticproject.exceptions.classes.base.NotFoundException;
import com.example.logisticproject.exceptions.classes.common.AttachmentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleAlreadyExistsException(AlreadyExistsException e) {
        Map<String, String> map = new HashMap<>();
        map.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(map);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException e) {
        Map<String, String> map = new HashMap<>();
        map.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}
