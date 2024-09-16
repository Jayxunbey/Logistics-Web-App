package com.example.logisticproject.repo;

import com.example.logisticproject.entity.Region;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Integer> {

    @Query(value = "select * from public.region r where upper(r.name_en) = upper(?1)",nativeQuery = true)
    Optional<Region> findByNameEnIgnoreCase(String nameEn);

}