package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.orderstatus.OrderStatusAddReqDto;
import com.example.logisticproject.entity.CargoType;
import com.example.logisticproject.entity.OrderStatus;
import com.example.logisticproject.repo.OrderStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusService(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    public void add(OrderStatusAddReqDto orderStatusAddReqDto) {
        checkIsExists(orderStatusAddReqDto);
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setNameEn(orderStatusAddReqDto.getNameEn());
        orderStatusRepository.save(orderStatus);
    }

    private void checkIsExists(OrderStatusAddReqDto orderStatusAddReqDto) {
        Optional<OrderStatus> byName = orderStatusRepository.findByName(orderStatusAddReqDto.getNameEn());
        if (byName.isPresent()) {
            throw new RuntimeException("OrderStatus with name " + orderStatusAddReqDto.getNameEn() + " already exists");
        }
    }

    public List<OrderStatus> getAll() {
        return orderStatusRepository.findAll();
    }
}
