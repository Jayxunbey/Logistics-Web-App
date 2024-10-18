package com.example.logisticproject.dto.req.roadtransport;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link com.example.logisticproject.entity.RoadTransport}
 */
@Getter
@Setter
public class RoadTransportChangeActiveDto {

    @NotNull
    @JsonProperty(value = "road_transport_id")
    Integer roadTransportId;

    @NotNull
    @JsonProperty(value = "active")
    Boolean active;

}