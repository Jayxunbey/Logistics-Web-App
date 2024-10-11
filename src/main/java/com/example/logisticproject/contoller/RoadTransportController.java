package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.roadtransport.RoadTransportAddingDto;
import com.example.logisticproject.dto.req.roadtransport.RoadTransportChangeActiveDto;
import com.example.logisticproject.dto.req.roadtransport.RoadTransportUpdatingDto;
import com.example.logisticproject.service.RoadTransportService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/road-transport")
public class RoadTransportController {

    private final RoadTransportService roadTransportService;

    public RoadTransportController(RoadTransportService roadTransportService) {
        this.roadTransportService = roadTransportService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> addRoadTransport(@RequestBody @Validated RoadTransportAddingDto roadTransportAddingDto) {
        roadTransportService.adding(roadTransportAddingDto);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/updating", method = RequestMethod.POST)
    public ResponseEntity<Object> addRoadTransport(@RequestBody @Validated RoadTransportUpdatingDto roadTransportUpdatingDto) {
        roadTransportService.updating(roadTransportUpdatingDto);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/change-active", method = RequestMethod.POST)
    public ResponseEntity<Object> addRoadTransport(@RequestBody @Validated RoadTransportChangeActiveDto roadTransportChangeActiveDto) {
        roadTransportService.changeActive(roadTransportChangeActiveDto);
        return ResponseEntity.ok().build();
    }

}
