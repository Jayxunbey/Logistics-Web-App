package com.example.logisticproject.repo;

import com.example.logisticproject.entity.RoadBetweenRegion;
import com.example.logisticproject.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TransportRepository extends JpaRepository<Transport, UUID> {
    @Query(value = "select * from transport order by type_id limit :size offset (:page-1)*:size",nativeQuery = true )
    List<Transport> findAsPagination(Integer page, Integer size);


}