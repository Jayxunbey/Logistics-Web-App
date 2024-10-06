package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.fortransportoption.ForTransportOptionsReqDto;
import org.springframework.stereotype.Service;

@Service
public class TransportOptionsAndOrderDetailsService {

    private final RoadTransportService roadTransportService;

    public TransportOptionsAndOrderDetailsService(RoadTransportService roadTransportService) {
        this.roadTransportService = roadTransportService;
    }

    public void findBy(ForTransportOptionsReqDto forTransportOptionsReqDto) {

        roadTransportService.find(
                forTransportOptionsReqDto.getFromId(),
                forTransportOptionsReqDto.getToId(),
                forTransportOptionsReqDto.isComeBack());

    }
}
