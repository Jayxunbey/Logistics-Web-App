package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.paymenttype.PaymentTypeAddReqDto;
import com.example.logisticproject.entity.PaymentType;
import com.example.logisticproject.repo.PaymentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepository;

    public PaymentTypeService(PaymentTypeRepository paymentTypeRepository) {
        this.paymentTypeRepository = paymentTypeRepository;
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

//    public List<PaymentType> getAll() {
////        paymentTypeRepository.find
//    }
}
