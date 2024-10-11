package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.whopay.WhoPayAddReqDto;
import com.example.logisticproject.service.WhoPayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/who-pay")
public class WhoPayController {


    private final WhoPayService whoPayService;

    public WhoPayController(WhoPayService whoPayService) {
        this.whoPayService = whoPayService;
    }



    @Operation(summary = "Add a new WhoPay", description = "Allows the user to add a new WhoPay")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "A new WhoPay added successfully",
                    content = @Content(mediaType = "application/json")
            )
    }
    )
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestBody @Validated WhoPayAddReqDto whoPayAddReqDto ){

        whoPayService.add(whoPayAddReqDto);

        return ResponseEntity.ok().build();


    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<Object> get( ){

        return ResponseEntity.ok(whoPayService.get());

    }

    @GetMapping("/get-via-lang")
    public ResponseEntity<Object> getRegions(@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) Locale locale) {

        System.out.println("locale codi = " + locale);
        return ResponseEntity.ok().build();

    }


}
