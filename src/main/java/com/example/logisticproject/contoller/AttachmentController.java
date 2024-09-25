package com.example.logisticproject.contoller;

import com.example.logisticproject.service.AttachmentService;
import com.example.logisticproject.service.ReactiveRedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

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
    public ResponseEntity<Map<String, String>> add(@RequestParam("file") MultipartFile file) {

        String fileId = attachmentService.upload(file);

        return ResponseEntity.ok(Map.of("id",fileId));


    }



    @RequestMapping("/check-is-available")
    public ResponseEntity<Map<String, Object>> noticeAboutStaying(@RequestParam("id" ) String key) {
        Object jay = reactiveRedisService.getPhotoData(key);
        return ResponseEntity.ok(Map.of("status",jay));

    }

//
//    @RequestMapping(value = "/get",method = RequestMethod.GET)
//    public ResponseEntity<Object> get(@RequestParam("key" ) String key) {
//
//
//
//        return ResponseEntity.ok(jay);
//
//    }

}
