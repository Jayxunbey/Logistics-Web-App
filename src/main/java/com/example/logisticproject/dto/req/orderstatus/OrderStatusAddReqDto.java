package com.example.logisticproject.dto.req.orderstatus;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.logisticproject.entity.OrderStatus}
 */
@Getter
@Setter
public class OrderStatusAddReqDto {
    @NotEmpty
    String nameEn;
}