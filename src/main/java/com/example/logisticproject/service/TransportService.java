package com.example.logisticproject.service;

import com.example.logisticproject.dto.resp.transport.TransportResponse;
import com.example.logisticproject.dto.req.transport.TransportAddingReqDto;
import com.example.logisticproject.entity.Attachment;
import com.example.logisticproject.entity.Transport;
import com.example.logisticproject.entity.TransportType;
import com.example.logisticproject.mapper.TransportMapper;
import com.example.logisticproject.repo.TransportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransportService {

    private final TransportTypeService transportTypeService;
    private final AttachmentService attachmentService;
    private final TransportMapper transportMapper;
    private final TransportRepository transportRepository;
    private final ModelMapper modelMapper;

    public TransportService(TransportTypeService transportTypeService, AttachmentService attachmentService,
                            TransportMapper transportMapper,
                            TransportRepository transportRepository,
                            ModelMapper modelMapper) {
        this.transportTypeService = transportTypeService;
        this.attachmentService = attachmentService;
        this.transportMapper = transportMapper;
        this.transportRepository = transportRepository;
        this.modelMapper = modelMapper;
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

    public Page<TransportResponse> getAsPage(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Transport> page = transportRepository.findAll(pageable);


        Page<TransportResponse> map = page.map((element) -> modelMapper.map(element, TransportResponse.class));

        return map;
    }
}
