package com.example.logisticproject.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleShortDto implements Serializable {
    private UUID id;
    private String name;
    private String code;
    private String description;
}
