package com.example.logisticproject.repo;

import com.example.logisticproject.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {
  @Query("select p from PaymentType p where upper(p.nameEn) = upper(?1)")
  Optional<PaymentType> findByNameEnIgnoreCase(String nameEn);
}