package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.orderstatus.OrderStatusAddReqDto;
import com.example.logisticproject.entity.OrderStatus;
import com.example.logisticproject.service.OrderStatusService;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestBody @Validated OrderStatusAddReqDto orderStatusAddReqDto) {

        orderStatusService.add(orderStatusAddReqDto);

        return ResponseEntity.ok().build();

    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<OrderStatus>> getAll() {

        List<OrderStatus> all = orderStatusService.getAll();

        return ResponseEntity.ok(all);

    }


}
