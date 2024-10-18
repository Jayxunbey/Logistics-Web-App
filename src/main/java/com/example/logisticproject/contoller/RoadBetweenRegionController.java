package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.roadbetweenregion.RoadBetweenRegionAddingReqDto;
import com.example.logisticproject.dto.req.roadbetweenregion.RoadBetweenRegionChangeActiveReqDto;
import com.example.logisticproject.dto.resp.region.RegionResponse;
import com.example.logisticproject.dto.resp.roadbetweenregion.RoadBetweenRegionPaginationRespDto;
import com.example.logisticproject.entity.RoadBetweenRegion;
import com.example.logisticproject.service.RoadBetweenRegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Add a RoadBetweenRegion", description = "Allows the user to add a new RoadBetweenRegion")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody @Validated RoadBetweenRegionAddingReqDto roadBetweenRegionAddingReqDto){

        roadBetweenRegionService.add(roadBetweenRegionAddingReqDto);

    }

//    @Operation(summary = "Change isActive status of the RoadBetweenRegion")
//    @RequestMapping(value = "/change-active", method = RequestMethod.POST)
//    public void changeActive(@RequestBody @Validated RoadBetweenRegionChangeActiveReqDto roadBetweenRegionChangeActiveReqDto){
//
//        roadBetweenRegionService.changeActive(roadBetweenRegionChangeActiveReqDto);
//
//    }


    @Operation(summary = "Get RoadBetweenRegion as a page", description = "Allows the user to get RoadBetweenRegion as a page")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "RoadBetweenRegion successfully sent",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoadBetweenRegionPaginationRespDto.class))
            )
    })
    @RequestMapping(value = "/get-as-page", method = RequestMethod.GET)
    public ResponseEntity<RoadBetweenRegionPaginationRespDto> changeActive(@RequestParam Integer page, @RequestParam Integer size){

        return ResponseEntity.ok(roadBetweenRegionService.getAsPage(page, size));

    }

    @RequestMapping(value = "/find-head-regions", method = RequestMethod.GET)
    public ResponseEntity<List<RegionResponse>> findRegion(@RequestParam(defaultValue = "") String name){

        List<RegionResponse> regionResponses = roadBetweenRegionService.searchByName(name);

        return ResponseEntity.ok(regionResponses);

    }

    @RequestMapping(value = "/find-connected-regions", method = RequestMethod.GET)
    public ResponseEntity<List<RegionResponse>> findConnectedRegion(@RequestParam(defaultValue = "") String name, @RequestParam(name = "region_id") Integer regionId ){

        List<RegionResponse> regionResponses = roadBetweenRegionService.searchConnectedRegions(name, regionId);

        return ResponseEntity.ok(regionResponses);

    }





}
