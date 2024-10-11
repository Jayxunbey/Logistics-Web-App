package com.example.logisticproject.dto.req.fortransportoption;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForTransportOptionsReqDto {

    @JsonProperty("from_id")
    private Integer fromId;

    @JsonProperty("to_id")
    private Integer toId;

    @JsonProperty("is_come_back")
    private boolean isComeBack;

}
