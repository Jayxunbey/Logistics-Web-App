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
@Table(name = "region")
public class Region extends Auditable {

    @Size(max = 255)
    @NotNull
    @Column(name = "name_en", nullable = false, unique = true)
    private String nameEn;

}