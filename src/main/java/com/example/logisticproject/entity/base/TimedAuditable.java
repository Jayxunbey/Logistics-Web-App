package com.example.logisticproject.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Clock;
import java.time.LocalDateTime;
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TimedAuditable {
    @JsonIgnore
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onPrePersist() {
        createdAt = LocalDateTime.now(Clock.systemDefaultZone());
        updatedAt = createdAt;
    }

    @PreUpdate
    public void onPreUpdate() {
        updatedAt = LocalDateTime.now(Clock.systemDefaultZone());
    }
}
