package com.example.logisticproject.dto.req.roadbetweenregion;

import com.example.logisticproject.entity.RoadBetweenRegion;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * DTO for {@link RoadBetweenRegion}
 */
@Getter
@Setter
public class RoadBetweenRegionChangeActiveReqDto {
    @NotNull
    UUID id;
    @NotNull
    Boolean active;

}
