package com.example.logisticproject.repo;

import com.example.logisticproject.entity.RoadBetweenRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoadBetweenRegionRepository extends JpaRepository<RoadBetweenRegion, Integer> {

    @Query("select (count(r) > 0) from RoadBetweenRegion r where r.fromAddress.id = ?1 and r.toAddress.id = ?2")
    boolean checkIsRoadExists(Integer fromId, Integer toId);

    @Transactional
    @Modifying
    @Query("update RoadBetweenRegion r set r.active = ?1 where r.id = ?2")
    int changeActive(Boolean active, Integer id);

    @Query(value = "select * from road_between_region order by from_address_id limit :size offset (:page-1)*:size",nativeQuery = true )
    List<RoadBetweenRegion> findAsPagination(Integer page, Integer size);
}