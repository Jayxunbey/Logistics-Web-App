package com.example.logisticproject.entity;

import com.example.logisticproject.entity.auth.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "order_history")
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('order_history_id_seq')")
    @Column(name = "id", nullable = false)
    private Long id;

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

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Size(max = 255)
    @NotNull
    @Column(name = "price", nullable = false)
    private String price;

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