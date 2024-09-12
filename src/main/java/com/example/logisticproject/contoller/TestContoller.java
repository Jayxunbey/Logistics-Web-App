package com.example.logisticproject.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestContoller {

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String hi() {
        return "hi";
    }


}
