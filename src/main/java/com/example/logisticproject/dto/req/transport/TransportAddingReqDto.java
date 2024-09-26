package com.example.logisticproject.dto.req.transport;

import com.example.logisticproject.entity.Attachment;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.example.logisticproject.entity.Transport}
 */
@Getter
@Setter
@ToString
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
    Double length;
    @NotNull
    Double height;
    @NotNull
    Double width;

    @JsonProperty("photo_attachment_id")
    private String photoId;

    @NotNull
    @JsonProperty("can_be_fully")
    Boolean canBeFully;

    @NotNull
    @JsonProperty("can_be_partially")
    Boolean canBePartially;
}