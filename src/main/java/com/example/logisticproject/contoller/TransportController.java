package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.transport.TransportAddingReqDto;
import com.example.logisticproject.service.TransportService;
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
    public void add(@RequestBody @Validated TransportAddingReqDto transportAddingReqDto) {
        transportService.add(transportAddingReqDto);
    }
}
