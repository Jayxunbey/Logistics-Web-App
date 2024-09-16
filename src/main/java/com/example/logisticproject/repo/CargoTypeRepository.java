package com.example.logisticproject.repo;

import com.example.logisticproject.entity.CargoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CargoTypeRepository extends JpaRepository<CargoType, Integer> {
  @Query("select c from CargoType c where upper(c.nameEn) = upper(?1)")
  Optional<CargoType> findByNameEn(String nameEn);
}