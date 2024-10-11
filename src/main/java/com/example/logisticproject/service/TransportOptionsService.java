package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.fortransportoption.ForTransportOptionsReqDto;
import com.example.logisticproject.dto.resp.transport.TransportRespForTOADSDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportOptionsService {

    private final RoadTransportService roadTransportService;

    public TransportOptionsService(RoadTransportService roadTransportService) {
        this.roadTransportService = roadTransportService;
    }

    public List<TransportRespForTOADSDto> findBy(ForTransportOptionsReqDto forTransportOptionsReqDto) {

       return roadTransportService.find(
                forTransportOptionsReqDto.getFromId(),
                forTransportOptionsReqDto.getToId(),
                forTransportOptionsReqDto.isComeBack());

    }
}
