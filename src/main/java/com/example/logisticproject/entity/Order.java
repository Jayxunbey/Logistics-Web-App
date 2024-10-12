package com.example.logisticproject.entity;

import com.example.logisticproject.entity.auth.User;
import com.example.logisticproject.entity.base.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends Auditable {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_type_id", nullable = false)
    private PaymentType paymentType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "who_pays_id", nullable = false)
    private WhoPay whoPays;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cargo_type_id", nullable = false)
    private CargoType cargoType;

    @Size(max = 255)
    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @Column(name = "lading_time", nullable = false)
    private Date ladingTime;

    @Size(max = 255)
    @NotNull
    @Column(name = "comment", nullable = false)
    private String comment;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Size(max = 255)
    @NotNull
    @Column(name = "calculated_price", nullable = false)
    private String calculatedPrice;

    @Size(max = 255)
    @NotNull
    @Column(name = "suggested_price", nullable = false)
    private String suggestedPrice;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private OrderStatus status;

    @NotNull
    @Column(name = "partial_percent", nullable = false)
    private Integer partialPercent;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}