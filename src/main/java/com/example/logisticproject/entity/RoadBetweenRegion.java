package com.example.logisticproject.entity;

import com.example.logisticproject.entity.base.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "road_between_region")
@NoArgsConstructor
@AllArgsConstructor
public class RoadBetweenRegion extends Auditable {


    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "from_address_id", nullable = false)
    private Region fromAddress;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "to_address_id", nullable = false)
    private Region toAddress;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = false;

}