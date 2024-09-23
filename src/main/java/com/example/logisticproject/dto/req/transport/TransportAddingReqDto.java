package com.example.logisticproject.dto.req.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.example.logisticproject.entity.Transport}
 */
@Getter
@Setter
public class TransportAddingReqDto implements Serializable {

    @NotEmpty
    String name;

    @NotNull
    @JsonProperty("transport_type_id")
    Integer transportTypeId;

    @NotNull
    @JsonProperty("max_capacity")
    Integer maxCapacity;
    @NotNull
    BigDecimal length;
    @NotNull
    BigDecimal height;
    @NotNull
    BigDecimal width;

    @NotNull
    @JsonProperty("can_be_fully")
    Boolean canBeFully;

    @NotNull
    @JsonProperty("can_be_partially")
    Boolean canBePartially;
}