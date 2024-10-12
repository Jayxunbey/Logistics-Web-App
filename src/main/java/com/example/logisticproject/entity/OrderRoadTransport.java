package com.example.logisticproject.entity;

import com.example.logisticproject.entity.base.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_road_transport")
public class OrderRoadTransport extends Auditable {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "road_transport_id", nullable = false)
    private RoadTransport roadTransport;

    @Size(max = 255)
    @NotNull
    @Column(name = "transport_price", nullable = false)
    private String transportPrice;

    @NotNull
    @Column(name = "is_directional", nullable = false)
    private Boolean isDirectional;

}