package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.roadbetweenregion.RoadBetweenRegionAddingReqDto;
import com.example.logisticproject.dto.req.roadbetweenregion.RoadBetweenRegionChangeActiveReqDto;
import com.example.logisticproject.dto.resp.roadbetweenregion.RoadBetweenRegionPaginationRespDto;
import com.example.logisticproject.entity.RoadBetweenRegion;
import com.example.logisticproject.service.RoadBetweenRegionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/road-between-region")
public class RoadBetweenRegionController {

    private final RoadBetweenRegionService roadBetweenRegionService;

    public RoadBetweenRegionController(RoadBetweenRegionService roadBetweenRegionService) {
        this.roadBetweenRegionService = roadBetweenRegionService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody @Validated RoadBetweenRegionAddingReqDto roadBetweenRegionAddingReqDto){

        roadBetweenRegionService.add(roadBetweenRegionAddingReqDto);

    }

    @RequestMapping(value = "/change-active", method = RequestMethod.POST)
    public void changeActive(@RequestBody @Validated RoadBetweenRegionChangeActiveReqDto roadBetweenRegionChangeActiveReqDto){

        roadBetweenRegionService.changeActive(roadBetweenRegionChangeActiveReqDto);

    }

    @RequestMapping(value = "/get-as-page", method = RequestMethod.GET)
    public ResponseEntity<RoadBetweenRegionPaginationRespDto> changeActive(@RequestParam Integer page, @RequestParam Integer size){

        return ResponseEntity.ok(roadBetweenRegionService.getAsPage(page, size));

    }

}
