package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.whopay.WhoPayAddReqDto;
import com.example.logisticproject.entity.WhoPay;
import com.example.logisticproject.repo.WhoPayRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WhoPayService {

    private final WhoPayRepository whoPayRepository;

    public WhoPayService(WhoPayRepository whoPayRepository) {
        this.whoPayRepository = whoPayRepository;
    }

    public void add(WhoPayAddReqDto whoPayAddReqDto) {
        checkExistsNames(whoPayAddReqDto);
        WhoPay whoPay = new WhoPay();
        whoPay.setNameEn(whoPayAddReqDto.getNameEn());
        whoPayRepository.save(whoPay);
    }

    private void checkExistsNames(WhoPayAddReqDto whoPayAddReqDto) {
        Optional<WhoPay> byNameEn = whoPayRepository.findByNameEn(whoPayAddReqDto.getNameEn());
        if (byNameEn.isPresent()) {
            throw new RuntimeException("this name available");
        }
    }
}
