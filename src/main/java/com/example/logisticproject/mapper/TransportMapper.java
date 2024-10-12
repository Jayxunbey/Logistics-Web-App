package com.example.logisticproject.mapper;

import com.example.logisticproject.dto.req.transport.TransportAddingReqDto;
import com.example.logisticproject.dto.resp.transport.TransportResponse;
import com.example.logisticproject.entity.Transport;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
public class TransportMapper {
    public Transport toEntity(TransportAddingReqDto transportAddingReqDto){
        return null;
    }

}