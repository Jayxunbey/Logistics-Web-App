package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.roadbetweenregion.RoadBeetwenRegionAddingReqDto;
import com.example.logisticproject.entity.RoadBetweenRegion;
import com.example.logisticproject.repo.RoadBetweenRegionRepository;
import com.example.logisticproject.repo.RoadTransportRepository;
import org.springframework.stereotype.Service;

@Service
public class RoadBetweenRegionService {

    private final RegionService regionService;
    private final RoadTransportRepository roadTransportRepository;
    private final RoadBetweenRegionRepository roadBetweenRegionRepository;

    public RoadBetweenRegionService(RegionService regionService,
                                    RoadTransportRepository roadTransportRepository,
                                    RoadBetweenRegionRepository roadBetweenRegionRepository) {
        this.regionService = regionService;
        this.roadTransportRepository = roadTransportRepository;
        this.roadBetweenRegionRepository = roadBetweenRegionRepository;
    }

    public void add(RoadBeetwenRegionAddingReqDto roadBeetwenRegionAddingReqDto) {
        regionService.checkRegion(roadBeetwenRegionAddingReqDto.getFromAddressId());
        regionService.checkRegion(roadBeetwenRegionAddingReqDto.getToAddressId());

        RoadBetweenRegion roadBetweenRegion = new RoadBetweenRegion();

        roadBetweenRegion.setFromAddress(roadBetweenRegion.getFromAddress());
        roadBetweenRegion.setToAddress(roadBetweenRegion.getToAddress());
        roadBetweenRegion.setActive(roadBetweenRegion.getActive());

        roadBetweenRegionRepository.save(roadBetweenRegion);

    }
}
