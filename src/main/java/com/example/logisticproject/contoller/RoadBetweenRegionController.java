package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.roadbetweenregion.RoadBeetwenRegionAddingReqDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/road-between-region")
public class RoadBetweenRegionController {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody @Validated RoadBeetwenRegionAddingReqDto roadBeetwenRegionAddingReqDto){

    }

}
