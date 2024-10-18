package com.example.logisticproject.repo;

import com.example.logisticproject.entity.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {


    @Query("from Role r join UserRole ur on r.id = ur.roleId where ur.userId = :userId and r.deleted = false and ur.deleted = false")
    List<Role> findByUser(@Param(value = "userId") UUID userId);
}
