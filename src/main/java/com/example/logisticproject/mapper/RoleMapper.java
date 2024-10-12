package com.example.logisticproject.mapper;

import com.example.logisticproject.dto.resp.RoleShortDto;
import com.example.logisticproject.entity.auth.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {

    RoleShortDto toRoleDto(Role role);
}
