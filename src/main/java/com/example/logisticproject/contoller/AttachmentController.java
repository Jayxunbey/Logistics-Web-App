package com.example.logisticproject.contoller;

import com.example.logisticproject.service.ReactiveRedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    private final ReactiveRedisService reactiveRedisService;

    public AttachmentController(ReactiveRedisService reactiveRedisService) {
        this.reactiveRedisService = reactiveRedisService;
    }

    @RequestMapping("/add")
    public void add() {

    }

    @RequestMapping("/notice-about-staying")
    public void noticeAboutStaying() {
        Mono<Boolean> booleanMono = reactiveRedisService.saveData("ikki", "Muxammadov");
        booleanMono.subscribe(System.out::println);
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseEntity<Mono<String>> get() {

        Mono<String> jay = reactiveRedisService.getData("ikki");

        return ResponseEntity.ok(jay);

    }

}
