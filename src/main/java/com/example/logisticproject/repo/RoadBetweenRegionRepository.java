package com.example.logisticproject.repo;

import com.example.logisticproject.entity.RoadBetweenRegion;
import com.example.logisticproject.projection.RegionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoadBetweenRegionRepository extends JpaRepository<RoadBetweenRegion, Integer> {

    @Query("select (count(r) > 0) from RoadBetweenRegion r where r.fromAddress.id = ?1 and r.toAddress.id = ?2")
    boolean checkIsRoadExists(Integer fromId, Integer toId);

    @Transactional
    @Modifying
    @Query("update RoadBetweenRegion r set r.active = ?1 where r.id = ?2")
    int changeActive(Boolean active, Integer id);

    @Query(nativeQuery = true,
            value = """
                    select searched.id as id, searched.name_en as nameEn
                    from (select region.id, region.name_en
                          from region
                                   inner join road_between_region on region.id = road_between_region.from_address_id or
                                                                     region.id = road_between_region.to_address_id
                          where name_en ilike concat('%', :text, '%')
                            and road_between_region.active = true
                          group by region.id) as searched
                    order by position(lower(:text) in lower(searched.name_en));

                    """)
    List<RegionProjection> searchBy(@Param("text") String text);

    @Query(value = "select * from road_between_region order by from_address_id limit :size offset (:page-1)*:size",nativeQuery = true )
    List<RoadBetweenRegion> findAsPagination(Integer page, Integer size);
}