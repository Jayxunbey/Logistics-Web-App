package com.example.logisticproject.dto.req.cargotype;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.logisticproject.entity.CargoType}
 */
@Getter
@Setter
public class CargoTypeAddReqDto {
    @NotEmpty
    String nameEn;
}