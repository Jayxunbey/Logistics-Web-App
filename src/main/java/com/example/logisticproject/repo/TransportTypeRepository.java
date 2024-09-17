package com.example.logisticproject.repo;

import com.example.logisticproject.entity.TransportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TransportTypeRepository extends JpaRepository<TransportType, Integer> {
    @Query("select t from TransportType t where upper(t.nameEn) = upper(?1)")
    Optional<TransportType> findByNameEn(String nameEn);
}