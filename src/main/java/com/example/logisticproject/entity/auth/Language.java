package com.example.logisticproject.entity.auth;

import com.example.logisticproject.entity.base.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Table(name = "setting_languages")
public class Language extends Auditable {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;
}
