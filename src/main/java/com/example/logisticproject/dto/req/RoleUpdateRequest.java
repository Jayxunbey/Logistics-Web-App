package com.example.logisticproject.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleUpdateRequest {
    private UUID id;
    private String name;
    private String code;
    private String description;
}
