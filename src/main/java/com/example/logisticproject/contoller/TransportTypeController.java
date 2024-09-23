package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.cargotype.CargoTypeAddReqDto;
import com.example.logisticproject.dto.req.transporttype.TransportTypeAddReqDto;
import com.example.logisticproject.entity.CargoType;
import com.example.logisticproject.entity.TransportType;
import com.example.logisticproject.service.CargoTypeService;
import com.example.logisticproject.service.TransportTypeService;
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
@RequestMapping("/api/transport-type")
public class TransportTypeController {


    private final TransportTypeService transportTypeService;

    public TransportTypeController(TransportTypeService transportTypeService) {
        this.transportTypeService = transportTypeService;
    }


    @Operation(summary = "Add a new type of Transport", description = "Allows the user to add a new type of Transport")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "A new type of Transport successfully added",
                    content = @Content(mediaType = "application/json")
            )
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestBody @Validated TransportTypeAddReqDto transportTypeAddReqDto ){

        transportTypeService.add(transportTypeAddReqDto);

        return ResponseEntity.ok().build();

    }

    @Operation(summary = "Get all types of transport", description = "Allows the user to get all types of transport")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All types of transport sent successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = TransportType.class))
            )
    })
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<TransportType>> getAll( ){

        List<TransportType> all = transportTypeService.getAll();

        return ResponseEntity.ok(all);

    }


}
