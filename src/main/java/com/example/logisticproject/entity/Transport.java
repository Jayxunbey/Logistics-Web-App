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
@Table(name = "transport")
public class Transport extends Auditable {

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "type_id", nullable = false)
    private TransportType type;

    @NotNull
    @Column(name = "max_capasity", nullable = false)
    private Integer maxCapacity;

    @NotNull
    @Column(name = "length", nullable = false)
    private Double length;

    @NotNull
    @Column(name = "height", nullable = false)
    private Double height;

    @NotNull
    @Column(name = "width", nullable = false)
    private Double width;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_attachment_id")
    private Attachment photoAttachment;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @NotNull
    @Column(name = "can_be_fully", nullable = false)
    private Boolean canBeFully = false;

    @NotNull
    @Column(name = "can_be_partially", nullable = false)
    private Boolean canBePartially = false;

}