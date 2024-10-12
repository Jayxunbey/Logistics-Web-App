package com.example.logisticproject.dto.resp.region;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegionResponse {
    private UUID id;
    private String nameEn;
}
