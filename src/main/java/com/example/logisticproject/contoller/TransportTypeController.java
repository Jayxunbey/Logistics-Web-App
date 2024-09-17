package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.cargotype.CargoTypeAddReqDto;
import com.example.logisticproject.dto.req.transporttype.TransportTypeAddReqDto;
import com.example.logisticproject.entity.CargoType;
import com.example.logisticproject.entity.TransportType;
import com.example.logisticproject.service.CargoTypeService;
import com.example.logisticproject.service.TransportTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transport-type")
public class TransportTypeController {


    private final TransportTypeService transportTypeService;

    public TransportTypeController(TransportTypeService transportTypeService) {
        this.transportTypeService = transportTypeService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestBody @Validated TransportTypeAddReqDto transportTypeAddReqDto ){

        transportTypeService.add(transportTypeAddReqDto);

        return ResponseEntity.ok().build();

    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<TransportType>> getAll( ){

        List<TransportType> all = transportTypeService.getAll();

        return ResponseEntity.ok(all);

    }


}
