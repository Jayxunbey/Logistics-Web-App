package com.example.logisticproject.dto.req.roadbetweenregion;

import com.example.logisticproject.entity.RoadBetweenRegion;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link RoadBetweenRegion}
 */
@Getter
@Setter
public class RoadBetweenRegionChangeActiveReqDto {
    @NotNull
    Integer id;
    @NotNull
    Boolean active;

}
