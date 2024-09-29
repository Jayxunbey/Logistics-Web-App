package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.roadbetweenregion.RoadBetweenRegionAddingReqDto;
import com.example.logisticproject.dto.req.roadbetweenregion.RoadBetweenRegionChangeActiveReqDto;
import com.example.logisticproject.dto.resp.region.RegionResponse;
import com.example.logisticproject.dto.resp.roadbetweenregion.RoadBetweenRegionPaginationRespDto;
import com.example.logisticproject.entity.Region;
import com.example.logisticproject.entity.RoadBetweenRegion;
import com.example.logisticproject.exceptions.classes.common.RoadBetweenRegionNotFoundException;
import com.example.logisticproject.projection.RegionProjection;
import com.example.logisticproject.repo.RoadBetweenRegionRepository;
import com.example.logisticproject.repo.RoadTransportRepository;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoadBetweenRegionService {

    private final RegionService regionService;
    private final RoadTransportRepository roadTransportRepository;
    private final RoadBetweenRegionRepository roadBetweenRegionRepository;
    private final ModelMapper modelMapper;

    public RoadBetweenRegionService(RegionService regionService,
                                    RoadTransportRepository roadTransportRepository,
                                    RoadBetweenRegionRepository roadBetweenRegionRepository,
                                    ModelMapper modelMapper) {
        this.regionService = regionService;
        this.roadTransportRepository = roadTransportRepository;
        this.roadBetweenRegionRepository = roadBetweenRegionRepository;
        this.modelMapper = modelMapper;
    }

    public void add(RoadBetweenRegionAddingReqDto roadBetweenRegionAddingReqDto) {
        checkIsExistRoadBetweenRegion(
                roadBetweenRegionAddingReqDto.getFromAddressId(),
                roadBetweenRegionAddingReqDto.getToAddressId());

        Region fromRegion = regionService.checkRegion(roadBetweenRegionAddingReqDto.getFromAddressId());
        Region toRegion = regionService.checkRegion(roadBetweenRegionAddingReqDto.getToAddressId());


        RoadBetweenRegion roadBetweenRegion = new RoadBetweenRegion();

        roadBetweenRegion.setFromAddress(fromRegion);
        roadBetweenRegion.setToAddress(toRegion);
        roadBetweenRegion.setActive(roadBetweenRegionAddingReqDto.getActive());

        roadBetweenRegionRepository.save(roadBetweenRegion);

    }

    private void checkIsExistRoadBetweenRegion(@NotNull Integer fromAddressId, @NotNull Integer toAddressId) {
        if (roadBetweenRegionRepository.checkIsRoadExists(fromAddressId, toAddressId)) {
            throw new RuntimeException("Road between regions already exists");
        }
    }

    public void changeActive(RoadBetweenRegionChangeActiveReqDto roadBetweenRegionChangeActiveReqDto) {

        Optional<RoadBetweenRegion> byId = roadBetweenRegionRepository.findById(roadBetweenRegionChangeActiveReqDto.getId());
        if (byId.isEmpty()) {
            throw new RuntimeException("Road between regions does not exist");
        }

        RoadBetweenRegion roadBetweenRegion = byId.get();
        if (roadBetweenRegion.getActive().booleanValue() == roadBetweenRegionChangeActiveReqDto.getActive().booleanValue()) {
            return;
        }

        int i = roadBetweenRegionRepository.changeActive(
                roadBetweenRegionChangeActiveReqDto.getActive(),
                roadBetweenRegionChangeActiveReqDto.getId());

        if (i == 0) {
            throw new RuntimeException("Road active change failed");
        }
    }

    public RoadBetweenRegionPaginationRespDto getAsPage(Integer page, Integer size) {

        List<RoadBetweenRegion> asPagination = roadBetweenRegionRepository.findAsPagination(page, size);

        long count = roadBetweenRegionRepository.count();

        Integer lastPage = (int) Math.ceil((double) count / (double) size);

        return RoadBetweenRegionPaginationRespDto
                .builder()
                .roadBetweenRegions(asPagination)
                .pageSize(size)
                .count((int) count)
                .current_page(page)
                .first_page(1)
                .last_page(lastPage)
                .build();


    }

    public List<RegionResponse> searchByName(String name) {

        List<RegionProjection> regions = roadBetweenRegionRepository.searchBy(name);

        return regions.stream().map((e) -> modelMapper.map(modelMapper.map(e, Region.class), RegionResponse.class)).collect(Collectors.toList());

    }

    public List<RegionResponse> searchConnectedRegions(String name, Integer regionId) {

        List<RegionProjection> regionProjections = roadBetweenRegionRepository.findConnectedRegionBy(name, regionId);

        return regionProjections.stream().map((e) -> modelMapper.map(modelMapper.map(e, Region.class), RegionResponse.class)).collect(Collectors.toList());

    }

    public RoadBetweenRegion get(Integer roadFromAddressId, Integer roadToAddressId) {
        Optional<RoadBetweenRegion> allOptions = roadBetweenRegionRepository.findAllOptions(roadFromAddressId, roadToAddressId);
        if (allOptions.isEmpty()) {
            throw new RoadBetweenRegionNotFoundException();
        }
        return allOptions.get();
    }
}
