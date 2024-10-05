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
public class RoadTransportUpdatingDto {

    @NotNull
    @JsonProperty(value = "from_address_id")
    Integer roadFromAddressId;

    @NotNull
    @JsonProperty(value = "to_address_id")
    Integer roadToAddressId;

    @NotNull
    String price;

    @NotNull
    @JsonProperty(value = "is_bilateral")
    Boolean isBilateral;

}