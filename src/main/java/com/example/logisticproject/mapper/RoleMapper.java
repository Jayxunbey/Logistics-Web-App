package com.example.logisticproject.mapper;

import com.example.logisticproject.dto.resp.RoleShortDto;
import com.example.logisticproject.entity.auth.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleShortDto toRoleDto(Role role) {
        return RoleShortDto.builder().build();
    }
}
