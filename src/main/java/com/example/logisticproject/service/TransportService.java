package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.transport.TransportAddingReqDto;
import org.springframework.stereotype.Service;

@Service
public class TransportService {

    private final TransportTypeService transportTypeService;

    public TransportService(TransportTypeService transportTypeService) {
        this.transportTypeService = transportTypeService;
    }

    public void add(TransportAddingReqDto transportAddingReqDto) {
        transportTypeService.checkIsExists(transportAddingReqDto.getTransportTypeId());

    }
}
