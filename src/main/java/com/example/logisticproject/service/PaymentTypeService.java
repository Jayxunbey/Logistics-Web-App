package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.paymenttype.PaymentTypeAddReqDto;
import com.example.logisticproject.dto.req.paymenttype.PaymentTypeEditActiveReqDto;
import com.example.logisticproject.dto.resp.paymenttype.PaymentTypeRespDto;
import com.example.logisticproject.entity.PaymentType;
import com.example.logisticproject.mapper.PaymentTypeMapper;
import com.example.logisticproject.repo.PaymentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepository;
    private final PaymentTypeMapper paymentTypeMapper;

    public PaymentTypeService(PaymentTypeRepository paymentTypeRepository,
                              PaymentTypeMapper paymentTypeMapper) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.paymentTypeMapper = paymentTypeMapper;
    }

    public void add(PaymentTypeAddReqDto paymentTypeAddReqDto) {
        checkIsExists(paymentTypeAddReqDto);
        PaymentType paymentType = new PaymentType();
        paymentType.setNameEn(paymentTypeAddReqDto.getNameEn());
        paymentType.setActive(false);
        paymentTypeRepository.save(paymentType);

    }

    private void checkIsExists(PaymentTypeAddReqDto paymentTypeAddReqDto) {
        Optional<PaymentType> byNameEnIgnoreCase = paymentTypeRepository.findByNameEnIgnoreCase(paymentTypeAddReqDto.getNameEn());
        if (byNameEnIgnoreCase.isPresent()) {
            throw new RuntimeException("This Payment Type already exists");
        }
    }

    public List<PaymentType> getAllWithOutActive() {
        return paymentTypeRepository.findAll();
    }

    public List<PaymentTypeRespDto> getAll() {
        List<PaymentType> activeIsTrue = paymentTypeRepository.findActiveIsTrue();

        List<PaymentTypeRespDto> respDtos = new ArrayList<>();

        for (PaymentType paymentType : activeIsTrue) {
            respDtos.add(paymentTypeMapper.toRespDto(paymentType));
        }

        return respDtos;
    }

    public PaymentType editActive(PaymentTypeEditActiveReqDto paymentTypeEditActiveReqDto) {

        Optional<PaymentType> byId = paymentTypeRepository.findById(paymentTypeEditActiveReqDto.getId());
        if (byId.isEmpty()) {
            throw new RuntimeException("This Payment Type does not exist");
        }

        PaymentType paymentType = byId.get();
        paymentType.setActive(paymentTypeEditActiveReqDto.getActive());

        return paymentTypeRepository.save(paymentType);
    }
}
