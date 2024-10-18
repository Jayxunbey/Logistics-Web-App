package com.example.logisticproject.dto.resp.transport;

import com.example.logisticproject.dto.resp.transporttype.TransportTypeRespDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.example.logisticproject.entity.Transport}
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransportRespForTOADSDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 255)
    String name;

    @NotNull
    TransportTypeRespDto type;

    @JsonProperty("max_capacity")
    @NotNull
    Integer maxCapacity;
    @NotNull
    Double length;
    @NotNull
    Double height;
    @NotNull
    Double width;

    @JsonProperty("photo_attachment_id")
    String photoAttachmentId;

    @JsonProperty("can_be_fully")
    @NotNull
    Boolean canBeFully;

    @JsonProperty("can_be_partially")
    @NotNull
    Boolean canBePartially;
}