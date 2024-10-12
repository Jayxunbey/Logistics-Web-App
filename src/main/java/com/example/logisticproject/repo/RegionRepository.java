package com.example.logisticproject.repo;

import com.example.logisticproject.entity.Region;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RegionRepository extends JpaRepository<Region, UUID> {

    @Query(value = "select * from public.region r where upper(r.name_en) = upper(?1)",nativeQuery = true)
    Optional<Region> findByNameEnIgnoreCase(String nameEn);

    @Query(nativeQuery = true,
            value = "select * from public.region where name_en ilike concat('%',:text,'%')")
    List<Region> searchBy(@Param("text") String text);

    @Query(nativeQuery = true, value = "select * from public.region r where r.name_en ilike concat('%',?,'%') and r.id <> ?")
    List<Region> searchWithoutId(String nameEn, UUID id);

}