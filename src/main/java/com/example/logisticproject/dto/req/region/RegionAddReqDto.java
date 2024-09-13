package com.example.logisticproject.dto.req.region;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegionAddReqDto {

    @NotEmpty
    private String nameEn;

}
