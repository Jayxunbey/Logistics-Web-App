package com.example.logisticproject.dto.req.paymenttype;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PaymentTypeEditActiveReqDto {
    @NotNull
    private UUID id;

    @NotNull
    private Boolean active;
}
