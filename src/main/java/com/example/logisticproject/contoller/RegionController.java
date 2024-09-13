package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.region.RegionAddReqDto;
import com.example.logisticproject.service.RegionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/region")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> addRegion(@RequestBody @Validated RegionAddReqDto region) {

        regionService.add(region);

        return ResponseEntity.ok().build();

    }

}
