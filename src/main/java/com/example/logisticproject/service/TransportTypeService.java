package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.transporttype.TransportTypeAddReqDto;
import com.example.logisticproject.dto.resp.transporttype.TransportTypeRespDto;
import com.example.logisticproject.entity.CargoType;
import com.example.logisticproject.entity.TransportType;
import com.example.logisticproject.exceptions.classes.common.TransportNotFoundException;
import com.example.logisticproject.repo.TransportTypeRepository;
import jakarta.validation.constraints.NotNull;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportTypeService {

    private final TransportTypeRepository transportTypeRepository;
    private final ModelMapper modelMapper;

    public TransportTypeService(TransportTypeRepository transportTypeRepository,
                                ModelMapper modelMapper) {
        this.transportTypeRepository = transportTypeRepository;
        this.modelMapper = modelMapper;
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

    public TransportType checkIsExists(@NotNull Integer transportTypeId) {
        Optional<TransportType> byId = transportTypeRepository.findById(transportTypeId);
        if (byId.isPresent()) {
            return byId.get();
        }

//        throw new TransportNotFoundException();
return null;
    }

    public TransportTypeRespDto findByIdWithForChecked(Integer typeId) {
        return modelMapper.map(transportTypeRepository.findById(typeId).get(), TransportTypeRespDto.class);
    }
}
