package com.example.logisticproject.repo;

import com.example.logisticproject.entity.Region;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Integer> {

    @Query(value = "select * from public.region r where upper(r.name_en) = upper(?1)",nativeQuery = true)
    Optional<Region> findByNameEnIgnoreCase(String nameEn);

    @Query(nativeQuery = true,
            value = "select * from public.region where name_en ilike concat('%',:text,'%') order by position(lower(:text) in lower(name_en))")
    List<Region> searchBy(@Param("text") String text);

    @Query(nativeQuery = true, value = "select * from public.region r where r.name_en ilike concat('%',?1,'%') and r.id <> ?2 order by position(lower(?1) in lower(name_en))")
    List<Region> searchWithoutId(String nameEn, Integer id);

}