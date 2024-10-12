package com.example.logisticproject.repo;

import com.example.logisticproject.entity.WhoPay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface WhoPayRepository extends JpaRepository<WhoPay, UUID> {
    @Query("select w from WhoPay w where w.nameEn = ?1")
    Optional<WhoPay> findByNameEn(String nameEn);
}