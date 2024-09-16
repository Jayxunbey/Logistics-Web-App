package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.req.whopay.WhoPayAddReqDto;
import com.example.logisticproject.service.WhoPayService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/who-pay")
public class WhoPayController {


    private final WhoPayService whoPayService;

    public WhoPayController(WhoPayService whoPayService) {
        this.whoPayService = whoPayService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestBody @Validated WhoPayAddReqDto whoPayAddReqDto ){

        whoPayService.add(whoPayAddReqDto);

        return ResponseEntity.ok().build();


    }


}
