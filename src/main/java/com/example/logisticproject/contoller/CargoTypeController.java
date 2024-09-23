package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.cargotype.CargoTypeAddReqDto;
import com.example.logisticproject.dto.req.whopay.WhoPayAddReqDto;
import com.example.logisticproject.entity.CargoType;
import com.example.logisticproject.service.CargoTypeService;
import com.example.logisticproject.service.WhoPayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Add a new cargo type",
            description = "Allows the user to add a new cargo type"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cargo type successfully added",
                    content = {@Content(mediaType = "application/json")}
            )
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestBody @Validated CargoTypeAddReqDto cargoTypeAddReqDto ){

        cargoTypeService.add(cargoTypeAddReqDto);

        return ResponseEntity.ok().build();

    }

    @Operation(
            summary = "Get all types of cargo",
            description = "Allows the user to retrieve all types of cargo"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cargo types successfully retrieved",
                    content = {@Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CargoType.class))}
            )
    })
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<CargoType>> getAll( ){

        List<CargoType> all = cargoTypeService.getAll();

        return ResponseEntity.ok(all);
    }


}
