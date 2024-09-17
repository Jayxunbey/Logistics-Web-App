package com.example.logisticproject.dto.req.transporttype;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link com.example.logisticproject.entity.TransportType}
 */
@Getter
@Setter
public class TransportTypeAddReqDto {
    @NotEmpty
    private String nameEn;
}
