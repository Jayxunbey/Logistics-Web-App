package com.example.logisticproject.entity;

import com.example.logisticproject.entity.base.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "road_transport")
public class RoadTransport extends Auditable {


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "road_id", nullable = false)
    private RoadBetweenRegion road;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transport_id", nullable = false)
    private Transport transport;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @NotNull
    @Column(name = "price", nullable = false, precision = 19, scale = 5)
    private BigDecimal price;

    @NotNull
    @Column(name = "is_directional", nullable = false)
    private Boolean isDirectional;

    @NotNull
    @Column(name = "is_bilateral", nullable = false)
    private Boolean isBilateral;

}