package com.example.logisticproject.dto.resp.paymenttype;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO for {@link com.example.logisticproject.entity.PaymentType}
 */

@Getter
@Setter
@Builder
public class PaymentTypeRespDto {

    private UUID id;

    private String nameEn;
}
