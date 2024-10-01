package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.fortransportoption.ForTransportOptionsReqDto;
import com.example.logisticproject.service.TransportOptionsAndOrderDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/transport-options-and-order-details")
public class TransportOptionsAndOrderDetailsController {

    private final TransportOptionsAndOrderDetailsService transportOptionsAndOrderDetailsService;

    public TransportOptionsAndOrderDetailsController(TransportOptionsAndOrderDetailsService transportOptionsAndOrderDetailsService) {
        this.transportOptionsAndOrderDetailsService = transportOptionsAndOrderDetailsService;
    }

    @RequestMapping("/find")
    public ResponseEntity<Object> findTransportOptionsAndOrderDetails(@RequestBody ForTransportOptionsReqDto forTransportOptionsReqDto) {

        transportOptionsAndOrderDetailsService.findBy(forTransportOptionsReqDto);


        return ResponseEntity.ok().build();

    }


}
