package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.region.RegionAddReqDto;
import com.example.logisticproject.entity.Region;
import com.example.logisticproject.repo.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
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

}

