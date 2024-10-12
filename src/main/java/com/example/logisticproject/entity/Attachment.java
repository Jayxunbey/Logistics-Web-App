package com.example.logisticproject.entity;

import com.example.logisticproject.entity.base.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "attachments")
@AllArgsConstructor
@NoArgsConstructor
public class Attachment extends Auditable {

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Size(max = 255)
    @NotNull
    @Column(name = "extension", nullable = false)
    private String extension;

    @Size(max = 255)
    @NotNull
    @Column(name = "path", nullable = false)
    private String path;

    @NotNull
    @Column(name = "active", nullable = false)
    private boolean active;

}