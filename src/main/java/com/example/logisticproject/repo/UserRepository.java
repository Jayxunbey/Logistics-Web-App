package com.example.logisticproject.repo;

import com.example.logisticproject.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    @Query("select t from User t where t.id=?1 and t.deleted = false")
    User findByIdAndDeleted(UUID id);
}
