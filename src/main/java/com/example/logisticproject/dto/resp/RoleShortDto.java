package com.example.logisticproject.dto.resp;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleShortDto implements Serializable {
    private UUID id;
    private String name;
    private String code;
    private String description;
}
