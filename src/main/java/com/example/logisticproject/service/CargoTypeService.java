package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.cargotype.CargoTypeAddReqDto;
import com.example.logisticproject.entity.CargoType;
import com.example.logisticproject.repo.CargoTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoTypeService {

    private final CargoTypeRepository cargoTypeRepository;

    public CargoTypeService(CargoTypeRepository cargoTypeRepository) {
        this.cargoTypeRepository = cargoTypeRepository;
    }

    public void add(CargoTypeAddReqDto cargoTypeAddReqDto) {
        chechIsExists(cargoTypeAddReqDto);

        CargoType entity = new CargoType();
        entity.setNameEn(cargoTypeAddReqDto.getNameEn());

        cargoTypeRepository.save(entity);

    }

    private void chechIsExists(CargoTypeAddReqDto cargoTypeAddReqDto) {
        if (cargoTypeRepository.findByNameEn(cargoTypeAddReqDto.getNameEn()).isPresent()) {
            throw new RuntimeException("Cargo type already exists");
        }
    }

    public List<CargoType> getAll() {
        List<CargoType> all = cargoTypeRepository.findAll();
        return all;
    }
}
