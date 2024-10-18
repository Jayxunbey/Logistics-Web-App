package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.paymenttype.PaymentTypeAddReqDto;
import com.example.logisticproject.dto.req.paymenttype.PaymentTypeEditActiveReqDto;
import com.example.logisticproject.dto.req.region.RegionAddReqDto;
import com.example.logisticproject.dto.resp.paymenttype.PaymentTypeRespDto;
import com.example.logisticproject.entity.OrderStatus;
import com.example.logisticproject.entity.PaymentType;
import com.example.logisticproject.service.PaymentTypeService;
import com.example.logisticproject.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/payment-type")
public class PaymentTypeController {

    private final PaymentTypeService paymentTypeService;

    public PaymentTypeController(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }



    @Operation(
            summary = "Add a new payment type",
            description = "Allows the user to add a new payment type"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Cargo type successfully added",
                    content = {@Content(mediaType = "application/json")}
            )
    })@RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestBody @Validated PaymentTypeAddReqDto paymentTypeAddReqDto) {

        paymentTypeService.add(paymentTypeAddReqDto);


        return ResponseEntity.ok().build();

    }

    @Operation(
            summary = "Get inactive payment types",
            description = "Allows the user to get payment types which are inactive"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Inactive payment types are successfully sent",
                    content = {@Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = PaymentType.class))}
            )
    })
    @RequestMapping(value = "/all-without-active", method = RequestMethod.GET)
    public ResponseEntity<List<PaymentType>> getAllWithoutActive() {
        return ResponseEntity.ok(paymentTypeService.getAllWithOutActive());
    }


    @Operation(
            summary = "Get all payment types",
            description = "Allows the user to retrieve all payment types"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All payment types successfully sent",
                    content = {@Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = PaymentTypeRespDto.class))}
            )
    })
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<PaymentTypeRespDto>> getAll() {
        return ResponseEntity.ok(paymentTypeService.getAll());
    }



    @Operation(
            summary = "Change isActive status",
            description = "Allows the user to edit the isActive status"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "The isActive status of the payment type successfully edited",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PaymentType.class))}
            )
    })
    @RequestMapping(value = "/edit-active", method = RequestMethod.POST)
    public ResponseEntity<PaymentType> editActive(@RequestBody @Validated PaymentTypeEditActiveReqDto paymentTypeEditActiveReqDto) {
        return ResponseEntity.ok(paymentTypeService.editActive(paymentTypeEditActiveReqDto));
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<Object> get( ){

        return ResponseEntity.ok(paymentTypeService.get());

    }


}
