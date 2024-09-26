package com.example.logisticproject.threads;

import com.example.logisticproject.entity.Attachment;
import com.example.logisticproject.service.AttachmentService;
import com.example.logisticproject.service.ReactiveRedisService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class SchedulingTasks {

    private final AttachmentService attachmentService;
    private final ReactiveRedisService reactiveRedisService;

    public SchedulingTasks(AttachmentService attachmentService, ReactiveRedisService reactiveRedisService) {
        this.attachmentService = attachmentService;
        this.reactiveRedisService = reactiveRedisService;
    }


    @Scheduled(fixedRateString = "${scheduling.tasks.auto-clear-false-attachment-with-checking-from-caching-on-minute}", timeUnit = TimeUnit.MINUTES)
    public void autoClearFalseAttachmentWithCheckingFromCaching(){

        List<Attachment> allWhichActiveFalse = attachmentService.getAllWhichActiveFalse();

        for (Attachment attachment : allWhichActiveFalse) {
            if (!reactiveRedisService.getPhotoData(attachment.getId())) {
                attachmentService.deleteFileByFullPath(attachment.getPath());
                attachmentService.deleteById(attachment.getId());
            }
        }


    }

}
