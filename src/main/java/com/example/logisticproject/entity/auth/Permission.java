package com.example.logisticproject.entity.auth;

import com.example.logisticproject.entity.base.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "permissions")
public class Permission extends Auditable {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    public String getAuthority() {
        return this.code;
    }
}
