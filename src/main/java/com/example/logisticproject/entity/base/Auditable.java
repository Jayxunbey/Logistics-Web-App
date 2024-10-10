package com.example.logisticproject.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdBy", "updatedBy",
                "createdAt", "updatedAt"},
        allowGetters = true
)
public class Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreatedBy
    @JsonIgnore
    @Column(name = "created_by")
    private UUID createdBy;

    @LastModifiedBy
    @JsonIgnore
    @Column(name = "updated_by")
    private UUID updatedBy;

    @JsonIgnore
    @CreationTimestamp
    @Setter(value = AccessLevel.PRIVATE)
    @Column(name = "created_at" /*nullable = false*/)
    private LocalDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @Setter(value = AccessLevel.PRIVATE)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @Column(name = "is_deleted", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean deleted;

    @JsonIgnore
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public void markAsDeleted() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
    }
}
