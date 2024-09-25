package com.example.logisticproject.service;

import com.example.logisticproject.contoller.exception.DataNotFoundException;
import com.example.logisticproject.dto.req.region.RegionAddReqDto;
import com.example.logisticproject.dto.resp.region.RegionResponse;
import com.example.logisticproject.entity.Region;
import com.example.logisticproject.repo.RegionRepository;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RegionService {

    private final RegionRepository regionRepository;
    private final ModelMapper modelMapper;

    public RegionService(RegionRepository regionRepository, ModelMapper modelMapper) {
        this.regionRepository = regionRepository;
        this.modelMapper = modelMapper;
    }

    public void add(RegionAddReqDto region) {
        Optional<Region> byNameEnIgnoreCase = regionRepository.findByNameEnIgnoreCase(region.getNameEn());
        if (byNameEnIgnoreCase.isPresent()) {
            throw new RuntimeException("Name already exists");
        }

        Region newRegion = new Region();

        newRegion.setNameEn(region.getNameEn());
        regionRepository.save(newRegion);

    }

    public List<Region> search(String text) {
        if (text == null || text.isEmpty()) {
            return List.of();
        }
        return regionRepository.searchBy(text);
    }

    public List<Region> searchWithoutId(String text, Integer exceptedRegionId) {

        if (text == null || text.isEmpty()) {
            return List.of();
        }

        if (exceptedRegionId == null) {
            throw new RuntimeException("Excepted region id is null");
        }

        List<Region> regions = regionRepository.searchWithoutId(text, exceptedRegionId);

        List<Region> result = new ArrayList<>();

        regions.stream().limit(4).forEach(result::add);

        return result;
    }

    public Region checkRegion(@NotNull Integer fromAddressId) {
        Optional<Region> byId = regionRepository.findById(fromAddressId);
        if (byId.isEmpty()){
            throw new RuntimeException("Region not found");
        }

        return byId.get();

    }

    public List<RegionResponse> getAll() {
        return regionRepository.findAll().stream().map(e -> modelMapper.map(e, RegionResponse.class)).collect(Collectors.toList());
    }

    public Region findById(Integer id) {
        return regionRepository.findById(id).orElseThrow(() -> new DataNotFoundException(String.format("Region with id %s not found", id)));
    }

    public RegionResponse update(int id, RegionAddReqDto update) {
        Region region = findById(id);
        region.setNameEn(update.getNameEn());
        return modelMapper.map(regionRepository.save(region), RegionResponse.class);
    }
}

