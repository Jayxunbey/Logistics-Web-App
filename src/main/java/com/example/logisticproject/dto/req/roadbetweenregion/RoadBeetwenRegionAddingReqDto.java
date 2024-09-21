package com.example.logisticproject.dto.req.roadbetweenregion;

import com.example.logisticproject.entity.RoadBetweenRegion;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link RoadBetweenRegion}
 */
@Getter
@Setter
public class RoadBeetwenRegionAddingReqDto implements Serializable {
    @NotNull
    @JsonProperty(required = true, value = "from_address_id")
    Integer fromAddressId;
    @NotNull
    @JsonProperty(required = true, value = "to_address_id")
    Integer toAddressId;

    @NotNull
    Boolean active;
}