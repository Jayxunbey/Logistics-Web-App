package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.roadbetweenregion.RoadBetweenRegionAddingReqDto;
import com.example.logisticproject.dto.req.roadbetweenregion.RoadBetweenRegionChangeActiveReqDto;
import com.example.logisticproject.dto.resp.roadbetweenregion.RoadBetweenRegionPaginationRespDto;
import com.example.logisticproject.entity.Region;
import com.example.logisticproject.entity.RoadBetweenRegion;
import com.example.logisticproject.repo.RoadBetweenRegionRepository;
import com.example.logisticproject.repo.RoadTransportRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        Integer lastPage = (int) Math.ceil((double)count/(double)size);

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
}
