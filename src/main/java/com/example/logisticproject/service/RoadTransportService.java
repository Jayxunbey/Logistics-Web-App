package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.roadtransport.RoadTransportAddingDto;
import com.example.logisticproject.entity.RoadBetweenRegion;
import com.example.logisticproject.entity.RoadTransport;
import com.example.logisticproject.entity.Transport;
import com.example.logisticproject.exceptions.classes.common.RoadTransportAlreadyExistsException;
import com.example.logisticproject.repo.RoadTransportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class RoadTransportService {
    private final RoadTransportRepository roadTransportRepository;
    private final RoadBetweenRegionService roadBetweenRegionService;
    private final TransportService transportService;

    public RoadTransportService(RoadTransportRepository roadTransportRepository, RoadBetweenRegionService roadBetweenRegionService, TransportService transportService) {
        this.roadTransportRepository = roadTransportRepository;
        this.roadBetweenRegionService = roadBetweenRegionService;
        this.transportService = transportService;
    }

    @Transactional
    public void adding(RoadTransportAddingDto roadTransportAddingDto) {
        RoadBetweenRegion roadBetweenRegion = roadBetweenRegionService.get(roadTransportAddingDto.getRoadFromAddressId(), roadTransportAddingDto.getRoadToAddressId());
        boolean isDirectional;

        if (checkIsExists(
                roadTransportAddingDto.getRoadFromAddressId(),
                roadTransportAddingDto.getRoadToAddressId(),
                roadTransportAddingDto.getTransportId()
        )) {
//            throw new RoadTransportAlreadyExistsException();
        }

        Transport transport = transportService.get(roadTransportAddingDto.getTransportId());

        if (roadBetweenRegion.getFromAddress().getId() == roadTransportAddingDto.getRoadFromAddressId()) {
            isDirectional = true;
        } else {
            isDirectional = false;
        }

        RoadTransport roadTransport = new RoadTransport();
        roadTransport.setTransport(transport);
        roadTransport.setRoad(roadBetweenRegion);
        roadTransport.setPrice(new BigDecimal(roadTransportAddingDto.getPrice()));
        roadTransport.setActive(false);
        roadTransport.setIsDirectional(isDirectional);
        roadTransport.setIsBilateral(roadTransportAddingDto.getIsBilateral());

        roadTransportRepository.save(roadTransport);

    }

    private boolean checkIsExists(Integer roadFromAddressId, Integer roadToAddressId, Integer transportId) {
        return roadTransportRepository.existsBy3Field(roadFromAddressId, roadToAddressId, transportId);
    }
}
