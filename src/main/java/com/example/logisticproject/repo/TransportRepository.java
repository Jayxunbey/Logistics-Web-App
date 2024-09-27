package com.example.logisticproject.repo;

import com.example.logisticproject.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<Transport, Integer> {
}