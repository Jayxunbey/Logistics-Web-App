package com.example.logisticproject.mapper;

import com.example.logisticproject.dto.req.roadtransport.RoadTransportAddingDto;
import com.example.logisticproject.entity.RoadTransport;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoadTransportMapper {
    @Mapping(source = "transportId", target = "transport.id")
    @Mapping(source = "roadToAddressId", target = "road.toAddress.id")
    @Mapping(source = "roadFromAddressId", target = "road.fromAddress.id")
    RoadTransport toEntity(RoadTransportAddingDto roadTransportAddingDto);

    @InheritInverseConfiguration(name = "toEntity")
    RoadTransportAddingDto toDto(RoadTransport roadTransport);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RoadTransport partialUpdate(RoadTransportAddingDto roadTransportAddingDto, @MappingTarget RoadTransport roadTransport);
}