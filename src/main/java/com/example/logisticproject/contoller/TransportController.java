package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.transport.TransportAddingReqDto;
import com.example.logisticproject.service.TransportService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/transport")
public class TransportController {

    private final TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @RequestMapping("/add")
    public ResponseEntity<Object> add(@RequestBody @Validated TransportAddingReqDto transportAddingReqDto) {
        transportService.add(transportAddingReqDto);
        return ResponseEntity.ok().build();
    }




}
