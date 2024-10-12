package com.example.logisticproject.dto.req;

import com.example.logisticproject.dto.BaseFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleRequest extends BaseFilter {
    private String name;
    private String code;
}
