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
import java.util.Optional;

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

        Transport transport = transportService.get(roadTransportAddingDto.getTransportId());

        boolean isDirectional;

        RoadTransport forSaving = null;
        RoadTransport forUpdating = null;

        if (roadBetweenRegion.getIsDirectional()) {

            Optional<RoadTransport> roadTransportOptional = roadTransportRepository.findByRoad_IdAndTransport_Id(roadBetweenRegion.getId(), roadTransportAddingDto.getTransportId());

            if (roadTransportOptional.isEmpty()) {
                RoadTransport roadTransport = new RoadTransport();
                roadTransport.setTransport(transport);
                roadTransport.setRoad(roadBetweenRegion);
                roadTransport.setIsDirectional(true);
                roadTransport.setPrice(new BigDecimal(roadTransportAddingDto.getPrice()));
                roadTransport.setIsBilateral(roadTransportAddingDto.getIsBilateral());
                forSaving = roadTransport;
            } else {
                throw new RoadTransportAlreadyExistsException();
            }

        } else {

            RoadBetweenRegion directionallyRoad = roadBetweenRegionService.get(
                    roadBetweenRegion.getToAddress().getId(),
                    roadBetweenRegion.getFromAddress().getId());

            Optional<RoadTransport> roadTransportOptional = roadTransportRepository.findByRoad_IdAndTransport_Id(
                    directionallyRoad.getId(),
                    roadTransportAddingDto.getTransportId());

            if (roadTransportOptional.isEmpty()) {
                RoadTransport roadTransport = new RoadTransport();
                roadTransport.setTransport(transport);
                roadTransport.setRoad(roadBetweenRegion);
                roadTransport.setPrice(new BigDecimal(roadTransportAddingDto.getPrice()));
                if (roadTransportAddingDto.getIsBilateral()) {
                    roadTransport.setIsBilateral(true);
                    roadTransport.setIsDirectional(true);
                } else {
                    roadTransport.setIsBilateral(false);
                    roadTransport.setIsDirectional(false);
                }

                forSaving = roadTransport;
            } else {
                forUpdating = roadTransportOptional.get();
                if (forUpdating.getIsBilateral()) {
                    throw new RoadTransportAlreadyExistsException();
                } else {
                    if (roadTransportAddingDto.getIsBilateral()) {
                        forUpdating.setIsBilateral(true);
                        forUpdating.setIsDirectional(true);
                    } else {
                        forUpdating.setIsBilateral(false);
                        forUpdating.setIsDirectional(false);
                    }
                    forUpdating.setPrice(new BigDecimal(roadTransportAddingDto.getPrice()));
                }
            }

        }

        if (forSaving != null) {
            roadTransportRepository.save(forSaving);
        }
        if (forUpdating != null) {
            roadTransportRepository.updatePriceAndIsDirectionalAndIsBilateralById(
                    forUpdating.getPrice(),
                    forUpdating.getIsDirectional(),
                    forUpdating.getIsBilateral(),
                    forUpdating.getId());
        }

//
//
//        if (checkIsExists(
//                roadTransportAddingDto.getRoadFromAddressId(),
//                roadTransportAddingDto.getRoadToAddressId(),
//                roadTransportAddingDto.getTransportId()
//        )) {
//            throw new RoadTransportAlreadyExistsException();
//        }
//
//        Transport transport = transportService.get(roadTransportAddingDto.getTransportId());
//
//        if (roadBetweenRegion.getFromAddress().getId() == roadTransportAddingDto.getRoadFromAddressId()) {
//            isDirectional = true;
//        } else {
//            isDirectional = false;
//        }
//
//        RoadTransport roadTransport = new RoadTransport();
//        roadTransport.setTransport(transport);
//        roadTransport.setRoad(roadBetweenRegion);
//        roadTransport.setPrice(new BigDecimal(roadTransportAddingDto.getPrice()));
//        roadTransport.setActive(false);
//        roadTransport.setIsDirectional(isDirectional);
//        roadTransport.setIsBilateral(roadTransportAddingDto.getIsBilateral());
//
//        roadTransportRepository.save(roadTransport);

    }

    private boolean checkIsExists(Integer roadFromAddressId, Integer roadToAddressId, Integer transportId) {
        return roadTransportRepository.existsBy3Field(roadFromAddressId, roadToAddressId, transportId);
    }

    public void find(Integer fromId, Integer toId, boolean comeBack) {

    }
}
