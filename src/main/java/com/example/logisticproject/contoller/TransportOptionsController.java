package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.fortransportoption.ForTransportOptionsReqDto;
import com.example.logisticproject.dto.resp.transport.TransportRespForTOADSDto;
import com.example.logisticproject.service.TransportOptionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/transport-options")
public class TransportOptionsController {

    private final TransportOptionsService transportOptionsService;

    public TransportOptionsController(TransportOptionsService transportOptionsService) {
        this.transportOptionsService = transportOptionsService;
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ResponseEntity<Object> findTransportOptions(@RequestBody ForTransportOptionsReqDto forTransportOptionsReqDto) {

        List<TransportRespForTOADSDto> by = transportOptionsService.findBy(forTransportOptionsReqDto);

        return ResponseEntity.ok(by);

    }


}
