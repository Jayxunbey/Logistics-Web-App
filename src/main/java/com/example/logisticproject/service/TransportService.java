package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.transport.TransportAddingReqDto;
import com.example.logisticproject.entity.Attachment;
import com.example.logisticproject.entity.Transport;
import com.example.logisticproject.entity.TransportType;
import com.example.logisticproject.mapper.TransportMapper;
import com.example.logisticproject.repo.TransportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransportService {

    private final TransportTypeService transportTypeService;
    private final AttachmentService attachmentService;
    private final TransportMapper transportMapper;
    private final TransportRepository transportRepository;

    public TransportService(TransportTypeService transportTypeService, AttachmentService attachmentService,
                            TransportMapper transportMapper,
                            TransportRepository transportRepository) {
        this.transportTypeService = transportTypeService;
        this.attachmentService = attachmentService;
        this.transportMapper = transportMapper;
        this.transportRepository = transportRepository;
    }

    @Transactional
    public void add(TransportAddingReqDto transportAddingReqDto) {
        TransportType transportType = transportTypeService.checkIsExists(transportAddingReqDto.getTransportTypeId());

        System.out.println("transportAddingReqDto = " + transportAddingReqDto);

        Attachment photo = attachmentService.getByIdWhichActiveFalse(transportAddingReqDto.getPhotoId());

        attachmentService.activationFile(photo.getId());

        Transport entity = transportMapper.toEntity(transportAddingReqDto);

        entity.setPhotoAttachment(photo);
        entity.setType(transportType);

        transportRepository.save(entity);

    }
}
