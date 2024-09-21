package com.example.logisticproject.dto.resp.roadbetweenregion;

import com.example.logisticproject.entity.RoadBetweenRegion;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
public class RoadBetweenRegionPaginationRespDto implements Serializable {

    @JsonProperty(value = "roads_arr")
    List<RoadBetweenRegion> roadBetweenRegions;

    @JsonProperty(value = "first_page")
    Integer first_page;

    @JsonProperty(value = "current_page")
    Integer current_page;

    @JsonProperty(value = "last_page")
    Integer last_page;

    @JsonProperty(value = "count")
    Integer count;

    @JsonProperty(value = "page_size")
    Integer pageSize;


}
