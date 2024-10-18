package com.example.logisticproject.repo;

import com.example.logisticproject.entity.RoadTransport;
import com.example.logisticproject.projection.TransportProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RoadTransportRepository extends JpaRepository<RoadTransport, Integer> {

    @Query("""
            select (count(r) > 0) from RoadTransport r
            where ((r.road.fromAddress.id = ?1 and r.road.toAddress.id = ?2) or (r.road.fromAddress.id = ?2 and r.road.toAddress.id = ?1)) and r.transport.id = ?3""")
    boolean existsBy3Field(Integer fromId, Integer toId, Integer transportId);


    @Query("""
            select (count(r) > 0) from RoadTransport r
            where ((r.road.fromAddress.id = ?1 and r.road.toAddress.id = ?2) or (r.road.fromAddress.id = ?2 and r.road.toAddress.id = ?1)) and r.transport.id = ?3""")
    List<RoadTransport> findRoadTransportIsTrue(Integer fromId, Integer toId, Integer transportId);


    Optional<RoadTransport> findByRoad_IdAndTransport_Id(Integer roadId, Integer transportId);

    @Transactional
    @Modifying
    @Query("update RoadTransport r set r.price = ?1, r.isDirectional = ?2, r.isBilateral = ?3 where r.id = ?4")
    int updatePriceAndIsDirectionalAndIsBilateralById(BigDecimal price, Boolean isDirectional, Boolean isBilateral, Integer id);

    @Query("""
            select r from RoadTransport r
            where (r.road.fromAddress.id = ?1 and r.road.toAddress.id = ?2)""")
    Optional<RoadTransport> findByRoad_FromAddress_IdAndRoad_ToAddress_Id(Integer roadFromAddressId, Integer roadToAddressId);

    @Transactional
    @Modifying
    @Query("update RoadTransport r set r.active = ?1 where r.id = ?2")
    int changeActive(Boolean active, Integer id);

    @Query(value = "select * from transportsbetweenregions(?1, ?2, ?3)", nativeQuery = true)
    List<TransportProjection> findConnectedTransportsUnique(Integer fromId, Integer toId, boolean comeBack);
}