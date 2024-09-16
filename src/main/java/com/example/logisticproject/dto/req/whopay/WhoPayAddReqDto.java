package com.example.logisticproject.dto.req.whopay;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class WhoPayAddReqDto {
    @NotEmpty
    private String nameEn;
}
