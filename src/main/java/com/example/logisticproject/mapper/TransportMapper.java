package com.example.logisticproject.mapper;

import com.example.logisticproject.dto.resp.transport.TransportResponse;
import com.example.logisticproject.dto.req.transport.TransportAddingReqDto;
import com.example.logisticproject.entity.Transport;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransportMapper {
    Transport toEntity(TransportAddingReqDto transportAddingReqDto);

    TransportAddingReqDto toDto(Transport transport);


    @Mapping(source = "photoAttachment.id", target = "photoAttachmentId")
    TransportResponse toRespDto(Transport transport);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "photoAttachmentId", target = "photoAttachment.id")
    Transport partialUpdate(TransportResponse transportResponse, @MappingTarget Transport transport);
}