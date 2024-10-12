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
@Table(name = "roles")
public class Role extends Auditable {

    @Column(nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "code")
    private String code;

    public String getAuthority() {
        return "ROLE_" + this.name.toUpperCase();
    }
}
