package com.example.logisticproject.mapper;

import com.example.logisticproject.dto.resp.paymenttype.PaymentTypeRespDto;
import com.example.logisticproject.entity.PaymentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Component
public class PaymentTypeMapper {

    public PaymentTypeRespDto toRespDto(PaymentType paymentType) {
        return PaymentTypeRespDto.builder().build();
    }
}
