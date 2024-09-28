package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.transport.TransportAddingReqDto;
import com.example.logisticproject.dto.resp.transport.TransportResponse;
import com.example.logisticproject.service.TransportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/transport")
public class TransportController {

    private final TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @RequestMapping("/add")
    public ResponseEntity<Object> add(@RequestBody @Validated TransportAddingReqDto transportAddingReqDto) {
        transportService.add(transportAddingReqDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get all regions", description = "Allows the user to get the data of all regions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all regions ",
                    content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = TransportController.class))
            )
    })
    @GetMapping
    public ResponseEntity<Page<TransportResponse>> getAll(
            @RequestParam(defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return ResponseEntity.ok(transportService.getAsPage(pageNo-1, pageSize));
    }




}
