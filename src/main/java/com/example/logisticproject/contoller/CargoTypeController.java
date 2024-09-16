package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.cargotype.CargoTypeAddReqDto;
import com.example.logisticproject.dto.req.whopay.WhoPayAddReqDto;
import com.example.logisticproject.entity.CargoType;
import com.example.logisticproject.service.CargoTypeService;
import com.example.logisticproject.service.WhoPayService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cargo-type")
public class CargoTypeController {


    private final CargoTypeService cargoTypeService;

    public CargoTypeController(CargoTypeService cargoTypeService) {
        this.cargoTypeService = cargoTypeService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestBody @Validated CargoTypeAddReqDto cargoTypeAddReqDto ){

        cargoTypeService.add(cargoTypeAddReqDto);

        return ResponseEntity.ok().build();

    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<CargoType>> getAll( ){

        List<CargoType> all = cargoTypeService.getAll();

        return ResponseEntity.ok(all);

    }


}
