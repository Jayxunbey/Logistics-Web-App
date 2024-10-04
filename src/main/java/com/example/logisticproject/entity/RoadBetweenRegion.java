package com.example.logisticproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "road_between_region")
@NoArgsConstructor
@AllArgsConstructor
public class RoadBetweenRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('road_beetwen_region_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "from_address_id", nullable = false)
    private Region fromAddress;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "to_address_id", nullable = false)
    private Region toAddress;


    @NotNull
    @Column(name = "is_directional", nullable = false)
    private Boolean isDirectional = true;
}