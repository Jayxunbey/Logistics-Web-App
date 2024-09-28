package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.region.RegionAddReqDto;
import com.example.logisticproject.dto.resp.region.RegionResponse;
import com.example.logisticproject.entity.Region;
import com.example.logisticproject.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/region")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }



    @Operation(summary = "Add a new region", description = "Allows the user to add a new region")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Region successfully added",
                        content = @Content(mediaType = "application/json"))
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> addRegion(@RequestBody @Validated RegionAddReqDto region) {

        regionService.add(region);

        return ResponseEntity.ok().build();

    }


    @Operation(summary = "Search a region", description = "Allows the user to search for a region")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of regions fitting the request successfully sent",
                            content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = Region.class))),
    })
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<Region>> searchRegion(@RequestParam String name) {

        List<Region> searched = regionService.search(name);

        return ResponseEntity.ok(searched);

    }


    @Operation(summary = "Search skipping a region", description = "Allows the user to find regions without the one whose id is specified as a request parameter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of regions with the absence of the one whose id is specified as a request parameter",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = Region.class))
            )
    })
    @RequestMapping(value = "/search-without-region", method = RequestMethod.GET)
    public ResponseEntity<List<Region>> searchRegion(@RequestParam(value = "region") String name, @RequestParam("except-region-id") Integer exceptedRegionId) {

        List<Region> searched = regionService.searchWithoutId(name,exceptedRegionId);

        return ResponseEntity.ok(searched);

    }

    @Operation(summary = "Get all regions", description = "Allows the user to get the data of all regions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all regions ",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = RegionResponse.class))
            )
    })
    @GetMapping
    public ResponseEntity<Page<RegionResponse>> getAll(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.ok(regionService.getAsPage(pageNo, pageSize));
    }


    @Operation(summary = "Update the region", description = "Allows the user to update the data of region")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Region update",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegionResponse.class))
            )
    })
    @PutMapping("/{regionId}")
    public RegionResponse update(@PathVariable(name = "regionId") int id, @RequestBody @Validated RegionAddReqDto update) {
        return regionService.update(id, update);
    }


}
