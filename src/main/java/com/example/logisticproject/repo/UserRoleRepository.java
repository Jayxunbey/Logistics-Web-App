package com.example.logisticproject.repo;

import com.example.logisticproject.entity.auth.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
}
