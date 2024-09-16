package com.example.logisticproject.dto.resp.paymenttype;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link com.example.logisticproject.entity.PaymentType}
 */

@Getter
@Setter
public class PaymentTypeRespDto {

    private Integer id;

    private String nameEn;
}
