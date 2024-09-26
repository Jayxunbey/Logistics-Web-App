package com.example.logisticproject.service;

import com.example.logisticproject.dto.req.transport.TransportAddingReqDto;
import com.example.logisticproject.entity.Attachment;
import com.example.logisticproject.entity.Transport;
import org.springframework.stereotype.Service;

@Service
public class TransportService {

    private final TransportTypeService transportTypeService;
    private final AttachmentService attachmentService;

    public TransportService(TransportTypeService transportTypeService, AttachmentService attachmentService) {
        this.transportTypeService = transportTypeService;
        this.attachmentService = attachmentService;
    }

    public void add(TransportAddingReqDto transportAddingReqDto) {
        transportTypeService.checkIsExists(transportAddingReqDto.getTransportTypeId());

        System.out.println("transportAddingReqDto = " + transportAddingReqDto);

        Attachment photo = attachmentService.getByIdWhichActiveFalse(transportAddingReqDto.getPhotoId());



        Transport transport = new Transport();





    }
}
