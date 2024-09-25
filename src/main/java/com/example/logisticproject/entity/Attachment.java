package com.example.logisticproject.entity;

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
@Table(name = "attachment")
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
    @Id
    @Size(max = 255)
    @Column(name = "id", nullable = false)
    private String id;

    @Size(max = 255)
    @NotNull
    @Column(name = "extension", nullable = false)
    private String extension;

    @Size(max = 255)
    @NotNull
    @Column(name = "path", nullable = false)
    private String path;

}