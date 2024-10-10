package com.example.logisticproject.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Roles {
    ADMIN("ADMIN"),
    USER("USER"),
    SUPPORT("SUPPORT");
    final String value;

}
