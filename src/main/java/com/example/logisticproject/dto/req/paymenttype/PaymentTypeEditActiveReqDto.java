package com.example.logisticproject.dto.req.paymenttype;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentTypeEditActiveReqDto {
    @NotNull
    private Integer id;

    @NotNull
    private Boolean active;
}
