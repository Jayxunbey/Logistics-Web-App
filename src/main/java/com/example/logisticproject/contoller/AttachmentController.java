package com.example.logisticproject.contoller;

import com.example.logisticproject.service.AttachmentService;
import com.example.logisticproject.service.ReactiveRedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    private final ReactiveRedisService reactiveRedisService;
    private final AttachmentService attachmentService;

    public AttachmentController(ReactiveRedisService reactiveRedisService, AttachmentService attachmentService) {
        this.reactiveRedisService = reactiveRedisService;
        this.attachmentService = attachmentService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestParam("file") MultipartFile file) {

        String fileId = attachmentService.upload(file);

        return ResponseEntity.ok(fileId);


    }

    @RequestMapping("/notice-about-staying")
    public void noticeAboutStaying(@RequestParam("key" ) String key, @RequestParam("value") String value) {
        Mono<Boolean> booleanMono = reactiveRedisService.saveData(key, value);
        booleanMono.subscribe(System.out::println);
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseEntity<Mono<String>> get(@RequestParam("key" ) String key) {

        Mono<String> jay = reactiveRedisService.getData(key);

        return ResponseEntity.ok(jay);

    }

}
