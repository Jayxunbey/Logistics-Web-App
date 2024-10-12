package com.example.logisticproject.dto.req.roadtransport;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link com.example.logisticproject.entity.RoadTransport}
 */
@Getter
@Setter
public class RoadTransportAddingDto {
    @NotNull
    @JsonProperty(value = "road_from_address_id")
    UUID roadFromAddressId;

    @NotNull
    @JsonProperty(value = "road_to_address_id")
    UUID roadToAddressId;

    @NotNull
    @JsonProperty(value = "transport_id")
    UUID transportId;

    @NotNull
    String price;

    @NotNull
    @JsonProperty(value = "is_bilateral")
    Boolean isBilateral;
}