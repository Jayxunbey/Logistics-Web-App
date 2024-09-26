package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.transporttype.TransportTypeAddReqDto;
import com.example.logisticproject.entity.CargoType;
import com.example.logisticproject.entity.TransportType;
import com.example.logisticproject.repo.TransportTypeRepository;
import jakarta.validation.constraints.NotNull;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportTypeService {

    private final TransportTypeRepository transportTypeRepository;

    public TransportTypeService(TransportTypeRepository transportTypeRepository) {
        this.transportTypeRepository = transportTypeRepository;
    }

    public void add(TransportTypeAddReqDto transportTypeAddReqDto) {

        chechIsExists(transportTypeAddReqDto);

        TransportType transportType = new TransportType();

        transportType.setNameEn(transportTypeAddReqDto.getNameEn());

        transportTypeRepository.save(transportType);


    }

    private void chechIsExists(TransportTypeAddReqDto transportTypeAddReqDto) {
        Optional<TransportType> byNameEn = transportTypeRepository.findByNameEn(transportTypeAddReqDto.getNameEn());
        if (byNameEn.isPresent()) {
            throw new RuntimeException("Transport type already exists");
        }
    }

    public List<TransportType> getAll() {
        return transportTypeRepository.findAll();
    }

    public void checkIsExists(@NotNull Integer transportTypeId) {
        if (!transportTypeRepository.existsById(transportTypeId)) {
            throw new RuntimeException("Transport type does not exist");
        }
    }
}
