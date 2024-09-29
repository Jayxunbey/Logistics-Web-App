package com.example.logisticproject.repo;

import com.example.logisticproject.entity.RoadTransport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoadTransportRepository extends JpaRepository<RoadTransport, Integer> {

    @Query("""
            select (count(r) > 0) from RoadTransport r
            where ((r.road.fromAddress.id = ?1 and r.road.toAddress.id = ?2) or (r.road.fromAddress.id = ?2 and r.road.toAddress.id = ?1)) and r.transport.id = ?3""")
    boolean existsBy3Field(Integer fromId, Integer toId, Integer transportId);
}