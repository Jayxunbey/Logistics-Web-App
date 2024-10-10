package com.example.logisticproject.repo;

import com.example.logisticproject.entity.auth.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {

    @Query("from Permission p join RolePermission pr on p.id = pr.permissionId where pr.roleId = :roleId and p.deleted = false and pr.deleted = false")
    List<Permission> findByRoleId(@Param("roleId") UUID roleId);
}
