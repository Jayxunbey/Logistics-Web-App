package com.example.logisticproject.mapper;

import com.example.logisticproject.dto.resp.paymenttype.PaymentTypeRespDto;
import com.example.logisticproject.entity.PaymentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentTypeMapper {

    @Mappings({@Mapping(target = "id", source = "id"), @Mapping(target = "nameEn", source = "nameEn")})
    PaymentTypeRespDto toRespDto(PaymentType paymentType);
}
