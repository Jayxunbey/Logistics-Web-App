package com.example.logisticproject.contoller;

import com.example.logisticproject.dto.DataDto;
import com.example.logisticproject.dto.req.AttachRoleToUserRequest;
import com.example.logisticproject.dto.req.RoleRequest;
import com.example.logisticproject.dto.req.RoleSaveRequest;
import com.example.logisticproject.dto.req.RoleUpdateRequest;
import com.example.logisticproject.dto.resp.RoleShortDto;
import com.example.logisticproject.service.RoleService;
import com.example.logisticproject.utils.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping(ApiConstants.API_VERSION + "/roles")
@Tag(name = "Role controller")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService service;

    @PostMapping(value = "/create")
    @Operation(description = "API for creating role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DataDto<UUID>> create(@RequestBody @Valid RoleSaveRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping(value = "/update")
    @Operation(description = "API for updating role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DataDto<UUID>> update(@RequestBody @Valid RoleUpdateRequest request) {
        return ResponseEntity.ok(service.update(request));
    }

    @GetMapping(value = "/{id}")
    @Operation(description = "API for get a role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DataDto<RoleShortDto>> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DataDto<List<RoleShortDto>>> getAll(RoleRequest request){
        return ResponseEntity.ok(service.getAll(request));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable UUID id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PostMapping(value = "/attach-to-user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DataDto<Boolean>> attachRoleToUser(@RequestBody @Valid AttachRoleToUserRequest request){
        return ResponseEntity.ok(service.attachRoleToUser(request));
    }
}
