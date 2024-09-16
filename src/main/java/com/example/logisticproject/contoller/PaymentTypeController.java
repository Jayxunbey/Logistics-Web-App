package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.paymenttype.PaymentTypeAddReqDto;
import com.example.logisticproject.dto.req.region.RegionAddReqDto;
import com.example.logisticproject.entity.PaymentType;
import com.example.logisticproject.service.PaymentTypeService;
import com.example.logisticproject.service.RegionService;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestBody @Validated PaymentTypeAddReqDto paymentTypeAddReqDto) {

        paymentTypeService.add(paymentTypeAddReqDto);


        return ResponseEntity.ok().build();

    }

    @RequestMapping(value = "/all-without-active", method = RequestMethod.GET)
    public ResponseEntity<List<PaymentType>> getAllWithoutActive() {
        return ResponseEntity.ok(paymentTypeService.getAllWithOutActive());
    }

//    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    public ResponseEntity<List<PaymentType>> getAll() {
////        return ResponseEntity.ok(paymentTypeService.getAll());
//    }


}
