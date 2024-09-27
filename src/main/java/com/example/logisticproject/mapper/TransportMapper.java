package com.example.logisticproject.mapper;

import com.example.logisticproject.dto.req.transport.TransportAddingReqDto;
import com.example.logisticproject.entity.Transport;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransportMapper {
    Transport toEntity(TransportAddingReqDto transportAddingReqDto);

    TransportAddingReqDto toDto(Transport transport);

}