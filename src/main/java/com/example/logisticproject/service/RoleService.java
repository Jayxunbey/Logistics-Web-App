package com.example.logisticproject.service;

import com.example.logisticproject.config.SessionProvider;
import com.example.logisticproject.dto.DataDto;
import com.example.logisticproject.dto.req.AttachRoleToUserRequest;
import com.example.logisticproject.dto.req.RoleRequest;
import com.example.logisticproject.dto.req.RoleSaveRequest;
import com.example.logisticproject.dto.req.RoleUpdateRequest;
import com.example.logisticproject.dto.resp.RoleShortDto;
import com.example.logisticproject.entity.auth.Role;
import com.example.logisticproject.entity.auth.User;
import com.example.logisticproject.entity.auth.UserRole;
import com.example.logisticproject.exceptions.classes.base.CustomNotFoundException;
import com.example.logisticproject.mapper.RoleMapper;
import com.example.logisticproject.repo.RoleRepository;
import com.example.logisticproject.repo.UserRepository;
import com.example.logisticproject.repo.UserRoleRepository;
import com.example.logisticproject.specifications.RoleSpecification;
import com.example.logisticproject.specifications.SearchSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repository;
    private final RoleMapper mapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public DataDto<UUID> create(RoleSaveRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        role.setCode(request.getCode());
        role.setDescription(request.getDescription());
        role.setCreatedBy(SessionProvider.getCurrentUser().getId());
        Role save = repository.save(role);
        return new DataDto<>(save.getId());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public DataDto<UUID> update(RoleUpdateRequest request) {
        Role role = repository.findByIdAndDeleted(request.getId());
        if (role == null)
            throw new CustomNotFoundException("role.not.found");

        role.setName(request.getName());
        role.setCode(request.getCode());
        role.setDescription(request.getDescription());
        role.setUpdatedBy(SessionProvider.getCurrentUser().getId());
        repository.save(role);
        return new DataDto<>(role.getId());
    }

    public DataDto<RoleShortDto> get(UUID id) {
        Role role = repository.findByIdAndDeleted(id);
        if (role == null)
            throw new CustomNotFoundException("role.not.found");
        return new DataDto<>(mapper.toRoleDto(role));
    }

    public DataDto<List<RoleShortDto>> getAll(RoleRequest request) {
        List<RoleShortDto> list = repository.findAll(new RoleSpecification(request),
                        SearchSpecification.getPageable(request.getPage(), request.getLimit(), "createdAt"))
                .map(mapper::toRoleDto)
                .toList();
        return new DataDto<>(list);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public DataDto<Boolean> delete(UUID id) {
        Role role = repository.findByIdAndDeleted(id);
        if (role == null)
            throw new CustomNotFoundException("role.not.found");
        role.markAsDeleted();
        repository.save(role);
        return new DataDto<>(true);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public DataDto<Boolean> attachRoleToUser(AttachRoleToUserRequest request) {
        User user = userRepository.findByIdAndDeleted(request.getUserId());
        if (user == null)
            throw new CustomNotFoundException("user.not.found");

        Role role = repository.findByIdAndDeleted(request.getRoleId());
        if (role == null)
            throw new CustomNotFoundException("role.not.found");

        UserRole userRole = new UserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(user.getId());
        userRole.setCreatedBy(SessionProvider.getCurrentUser().getId());
        userRoleRepository.save(userRole);
        return new DataDto<>(true);
    }
}
