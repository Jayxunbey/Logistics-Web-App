package com.example.logisticproject.entity;

import com.example.logisticproject.entity.base.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "order_history_road_transport")
public class OrderHistoryRoadTransport extends Auditable {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_history_id", nullable = false)
    private OrderHistory orderHistory;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "road_transport_id", nullable = false)
    private RoadTransport roadTransport;

    @Size(max = 255)
    @NotNull
    @Column(name = "transport_price", nullable = false)
    private String transportPrice;

}