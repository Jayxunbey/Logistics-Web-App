package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.orderstatus.OrderStatusAddReqDto;
import com.example.logisticproject.entity.CargoType;
import com.example.logisticproject.entity.OrderStatus;
import com.example.logisticproject.service.OrderStatusService;
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
@RequestMapping("/api/order-status")
public class OrderStatusController {


    private final OrderStatusService orderStatusService;

    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }


    @Operation(
            summary = "Add a new order status",
            description = "Allows the user to add a new order status"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Order status successfully added",
                    content = {@Content(mediaType = "application/json")}
            )
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestBody @Validated OrderStatusAddReqDto orderStatusAddReqDto) {

        orderStatusService.add(orderStatusAddReqDto);

        return ResponseEntity.ok().build();

    }

    @Operation(
            summary = "Get all all order statuses",
            description = "Allows the user to retrieve all order statuses"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Order statuses successfully sent",
                    content = {@Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = OrderStatus.class))}
            )
    })
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<OrderStatus>> getAll() {

        List<OrderStatus> all = orderStatusService.getAll();

        return ResponseEntity.ok(all);

    }


}
