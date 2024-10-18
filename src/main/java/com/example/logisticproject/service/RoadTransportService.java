package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.roadtransport.RoadTransportAddingDto;
import com.example.logisticproject.dto.req.roadtransport.RoadTransportChangeActiveDto;
import com.example.logisticproject.dto.req.roadtransport.RoadTransportUpdatingDto;
import com.example.logisticproject.dto.resp.transport.TransportRespForTOADSDto;
import com.example.logisticproject.entity.RoadBetweenRegion;
import com.example.logisticproject.entity.RoadTransport;
import com.example.logisticproject.entity.Transport;
import com.example.logisticproject.exceptions.classes.common.RoadTransportAlreadyExistsException;
import com.example.logisticproject.exceptions.classes.common.RoadTransportNotFoundException;
import com.example.logisticproject.projection.TransportProjection;
import com.example.logisticproject.repo.RoadTransportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoadTransportService {
    private final RoadTransportRepository roadTransportRepository;
    private final RoadBetweenRegionService roadBetweenRegionService;
    private final TransportService transportService;
    private final ModelMapper modelMapper;
    private final TransportTypeService transportTypeService;
    private final AttachmentService attachmentService;

    public RoadTransportService(RoadTransportRepository roadTransportRepository, RoadBetweenRegionService roadBetweenRegionService, TransportService transportService,
                                ModelMapper modelMapper, TransportTypeService transportTypeService, AttachmentService attachmentService) {
        this.roadTransportRepository = roadTransportRepository;
        this.roadBetweenRegionService = roadBetweenRegionService;
        this.transportService = transportService;
        this.modelMapper = modelMapper;
        this.transportTypeService = transportTypeService;
        this.attachmentService = attachmentService;
    }

    @Transactional
    public void adding(RoadTransportAddingDto roadTransportAddingDto) {
        RoadBetweenRegion roadBetweenRegion = roadBetweenRegionService.get(roadTransportAddingDto.getRoadFromAddressId(), roadTransportAddingDto.getRoadToAddressId());

        Transport transport = transportService.get(roadTransportAddingDto.getTransportId());

        RoadTransport forSaving = null;

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
//                throw new RoadTransportAlreadyExistsException();
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
//                throw new RoadTransportAlreadyExistsException();
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

    public List<TransportRespForTOADSDto> find(Integer fromId, Integer toId, boolean comeBack) {
        List<TransportProjection> connectedTransportsUniqueProjections = roadTransportRepository.findConnectedTransportsUnique(fromId, toId, comeBack);
        List<TransportRespForTOADSDto> result = new ArrayList<>();

        for (TransportProjection transportProjection : connectedTransportsUniqueProjections) {
            result.add(TransportRespForTOADSDto
                    .builder()
                    .id(transportProjection.getId())
                    .name(transportProjection.getName())
                    .canBePartially(transportProjection.isCanBePartially())
                    .canBeFully(transportProjection.isCanBeFully())
                    .width(transportProjection.getWidth())
                    .height(transportProjection.getHeight())
                    .length(transportProjection.getLength())
                    .maxCapacity(transportProjection.getMaxCapacity())
                    .type(transportTypeService.findByIdWithForChecked(transportProjection.getTypeId()))
                    .photoAttachmentId(transportProjection.getPhotoAttachmentId())
                    .build());
        }


        return result;

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
