package com.example.logisticproject.repo;

import com.example.logisticproject.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, UUID> {
  @Query("select o from OrderStatus o where upper(o.nameEn) = upper(?1)")
  Optional<OrderStatus> findByName(String nameEn);
}