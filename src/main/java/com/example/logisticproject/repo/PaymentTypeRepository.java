package com.example.logisticproject.repo;

import com.example.logisticproject.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, UUID> {
  @Query("select p from PaymentType p where upper(p.nameEn) = upper(?1)")
  Optional<PaymentType> findByNameEnIgnoreCase(String nameEn);

  @Query("select p from PaymentType p where p.active = true")
  List<PaymentType> findActiveIsTrue();
}