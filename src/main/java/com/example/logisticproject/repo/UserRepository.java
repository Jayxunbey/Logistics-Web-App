package com.example.logisticproject.repo;

import com.example.logisticproject.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);
}
