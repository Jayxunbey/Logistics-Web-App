package com.example.logisticproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    @Size(max = 255)
    @SequenceGenerator(name = "attachment_id_gen", sequenceName = "cargo_type_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private String id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @NotNull
    @Column(name = "extension", nullable = false)
    private String extension;

    @Size(max = 255)
    @NotNull
    @Column(name = "path", nullable = false)
    private String path;

}