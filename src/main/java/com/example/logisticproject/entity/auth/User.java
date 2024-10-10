package com.example.logisticproject.entity.auth;

import com.example.logisticproject.entity.Attachment;
import com.example.logisticproject.entity.base.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "users")
public class User extends Auditable {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name" /*nullable = false*/)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_attachment_id")
    private Attachment photoAttachment;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "region_id")
    private UUID regionId;

    @Column(name = "district_id")
    private UUID districtId;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "logo_url")
    private String logoUrl;

    @OneToOne
    @JoinColumn(name = "lang_id", referencedColumnName = "id")
    private Language language;

    public String getFullName() {
        StringBuilder str = new StringBuilder();
        str.append(this.firstName).append(" ");
        if (this.lastName != null)
            str.append(this.lastName).append(" ");
        if (this.middleName != null)
            str.append(this.middleName).append(" ");
        return str.toString();
    }

}