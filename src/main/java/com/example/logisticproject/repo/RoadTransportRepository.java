package com.example.logisticproject.repo;

import com.example.logisticproject.entity.RoadTransport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoadTransportRepository extends JpaRepository<RoadTransport, Integer> {
}