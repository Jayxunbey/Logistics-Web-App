package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.roadtransport.RoadTransportAddingDto;
import com.example.logisticproject.dto.req.roadtransport.RoadTransportChangeActiveDto;
import com.example.logisticproject.dto.req.roadtransport.RoadTransportUpdatingDto;
import com.example.logisticproject.entity.RoadBetweenRegion;
import com.example.logisticproject.entity.RoadTransport;
import com.example.logisticproject.entity.Transport;
import com.example.logisticproject.exceptions.classes.common.RoadTransportAlreadyExistsException;
import com.example.logisticproject.exceptions.classes.common.RoadTransportNotFoundException;
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

        RoadTransport forSaving;

        if (roadBetweenRegion.getIsDirectional()) {

            Optional<RoadTransport> roadTransportOptional = roadTransportRepository.findByRoad_IdAndTransport_Id(
                    roadBetweenRegion.getId(), roadTransportAddingDto.getTransportId());

            if (roadTransportOptional.isEmpty()) {
                forSaving = new RoadTransport();
                forSaving.setTransport(transport);
                forSaving.setRoad(roadBetweenRegion);
                forSaving.setIsDirectional(true);
                forSaving.setPrice(new BigDecimal(roadTransportAddingDto.getPrice()));
                forSaving.setIsBilateral(roadTransportAddingDto.getIsBilateral());

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

                forSaving = new RoadTransport();
                forSaving.setTransport(transport);
                forSaving.setRoad(directionallyRoad);
                forSaving.setPrice(new BigDecimal(roadTransportAddingDto.getPrice()));
                if (roadTransportAddingDto.getIsBilateral()) {
                    forSaving.setIsBilateral(true);
                    forSaving.setIsDirectional(true);
                } else {
                    forSaving.setIsBilateral(false);
                    forSaving.setIsDirectional(false);
                }

            } else {
                throw new RoadTransportAlreadyExistsException();
            }

        }

        roadTransportRepository.save(forSaving);

    }

    @Transactional
    public void updating(RoadTransportUpdatingDto roadTransportUpdatingDto) {
        RoadBetweenRegion roadBetweenRegion = roadBetweenRegionService.get(roadTransportUpdatingDto.getRoadFromAddressId(), roadTransportUpdatingDto.getRoadToAddressId());

        RoadTransport forUpdating;

        if (roadBetweenRegion.getIsDirectional()) {

            Optional<RoadTransport> roadTransportOptional = roadTransportRepository.findByRoad_FromAddress_IdAndRoad_ToAddress_Id(
                    roadTransportUpdatingDto.getRoadFromAddressId(),
                    roadTransportUpdatingDto.getRoadToAddressId());

            if (roadTransportOptional.isEmpty()) {

                throw new RoadTransportNotFoundException();

            } else {
                forUpdating = roadTransportOptional.get();

                forUpdating.setPrice(new BigDecimal(roadTransportUpdatingDto.getPrice()));
                forUpdating.setIsBilateral(roadTransportUpdatingDto.getIsBilateral());

            }

        } else {

            RoadBetweenRegion directionallyRoad = roadBetweenRegionService.get(
                    roadBetweenRegion.getToAddress().getId(),
                    roadBetweenRegion.getFromAddress().getId());

            Optional<RoadTransport> roadTransportOptional = roadTransportRepository.findByRoad_FromAddress_IdAndRoad_ToAddress_Id(
                    directionallyRoad.getFromAddress().getId(),
                    directionallyRoad.getToAddress().getId()
            );

            if (roadTransportOptional.isEmpty()) {

                throw new RoadTransportNotFoundException();

            } else {
                forUpdating = roadTransportOptional.get();

                if (roadTransportUpdatingDto.getIsBilateral()) {
                    forUpdating.setIsBilateral(true);
                    forUpdating.setIsDirectional(true);
                    forUpdating.setPrice(new BigDecimal(roadTransportUpdatingDto.getPrice()));
                } else {
                    forUpdating.setIsBilateral(false);
                    forUpdating.setIsDirectional(false);
                    forUpdating.setPrice(new BigDecimal(roadTransportUpdatingDto.getPrice()));
                }

            }

        }

        roadTransportRepository.updatePriceAndIsDirectionalAndIsBilateralById(
                forUpdating.getPrice(),
                forUpdating.getIsDirectional(),
                forUpdating.getIsBilateral(),
                forUpdating.getId());

    }


    private boolean checkIsExists(Integer roadFromAddressId, Integer roadToAddressId, Integer transportId) {
        return roadTransportRepository.existsBy3Field(roadFromAddressId, roadToAddressId, transportId);
    }

    public void find(Integer fromId, Integer toId, boolean comeBack) {

    }

    public void changeActive(RoadTransportChangeActiveDto roadTransportChangeActiveDto) {
        int i = roadTransportRepository.changeActive(
                roadTransportChangeActiveDto.getActive(),
                roadTransportChangeActiveDto.getRoadTransportId()
        );

        if (i < 1) {
            throw new RoadTransportNotFoundException();
        }
    }
}
