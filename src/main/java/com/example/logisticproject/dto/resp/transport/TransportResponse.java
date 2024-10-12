package com.example.logisticproject.dto.resp.transport;

import com.example.logisticproject.entity.TransportType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

/**
 * DTO for {@link com.example.logisticproject.entity.Transport}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TransportResponse {
    UUID id;
    @NotNull
    @Size(max = 255)
    String name;
    @NotNull
    TransportType type;
    @NotNull
            @JsonProperty("max-capacity")
    Integer maxCapacity;
    @NotNull
    Double length;
    @NotNull
    Double height;
    @NotNull
    Double width;

    @JsonProperty("photo-attachment")
    String photoAttachmentId;
    @NotNull
    Boolean active;
    @NotNull
    Boolean canBeFully;
    @NotNull
    Boolean canBePartially;
}