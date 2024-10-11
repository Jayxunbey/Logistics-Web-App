package com.example.logisticproject.dto.resp.transporttype;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.example.logisticproject.entity.TransportType}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransportTypeRespDto implements Serializable {

    Integer id;

    @JsonProperty("name_en")
    @NotNull
    @Size(max = 255)
    String nameEn;
}