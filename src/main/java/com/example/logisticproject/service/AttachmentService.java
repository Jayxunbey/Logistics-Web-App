package com.example.logisticproject.service;

import com.example.logisticproject.dto.between.DirectoryDetailsForSavingBetweenMethodsDto;
import com.example.logisticproject.entity.Attachment;
import com.example.logisticproject.repo.AttachmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service

public class AttachmentService {

    private static final Logger log = LoggerFactory.getLogger(AttachmentService.class);
    private final ReactiveRedisService reactiveRedisService;

    @Value("${file.saving.base-directory.path}")
    private String userDir;

    @Value("${file.saving.base-directory.if-cannot-access-the-directory-use-default-directory:false}")
    private boolean ifcannotAccessTheDirectoryUseDefaultDirectory;

    @Value("${file.saving.base-directory.if-dont-exist-the-directory-create:false}")
    private boolean ifdontExistTheDirectoryCreate;

    @Value("${file.saving.default-directory.is-enabled:false}")
    private boolean defaultDirectoryIsEnabled;


    @Value("${file.saving.base-directory.if-cant-save-use-default-directory:false}")
    private boolean ifNotExistSaveDefaultDirectory;
    private final AttachmentRepository attachmentRepository;

    public AttachmentService(AttachmentRepository attachmentRepository, ReactiveRedisService reactiveRedisService) {
        this.attachmentRepository = attachmentRepository;
        this.reactiveRedisService = reactiveRedisService;
    }


    @Transactional
    public String upload(MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }


        var savedFileDetails = checkDirectoryAndOtherAndGetObjAboutPath(file);


        try {
            file.transferTo(new File(savedFileDetails.getFullPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        savingToDatabase(savedFileDetails);

        reactiveRedisService.savePhotoData(savedFileDetails.getFileName());

        return savedFileDetails.getFileName();


    }

    private void savingToDatabase(DirectoryDetailsForSavingBetweenMethodsDto savedFileDetails) {
        attachmentRepository.save(new Attachment(
                savedFileDetails.getFileName(),
                savedFileDetails.getFileExtension(),
                savedFileDetails.getFullPath(),
                false)
        );


    }

    private DirectoryDetailsForSavingBetweenMethodsDto checkDirectoryAndOtherAndGetObjAboutPath(MultipartFile file) {

        var directoryDetails = new DirectoryDetailsForSavingBetweenMethodsDto();

        String fullPath;

        if (defaultDirectoryIsEnabled) {
            return connectToDefaultDirectory(file, directoryDetails);
        } else {

            if (userDir == null || userDir.isBlank()) {

                if (ifcannotAccessTheDirectoryUseDefaultDirectory) {
                    return connectToDefaultDirectory(file, directoryDetails);
                }

                log.info("is not available directory");
                throw new RuntimeException("Cannot save");

            } else {

                if (!checkIsAvailablePath(userDir)) {
                    if (ifdontExistTheDirectoryCreate) {
                        createPath(userDir);
                    } else {
                        log.info("is not available directory");
                        throw new RuntimeException("Cannot save");
                    }
                }

                fullPath = userDir + (!userDir.endsWith(File.separator) ? File.separator : "") + getFileBodyNameForSaving(file, directoryDetails);
                directoryDetails.setFullPath(fullPath);
                return directoryDetails;

            }
        }
    }

    private DirectoryDetailsForSavingBetweenMethodsDto connectToDefaultDirectory(MultipartFile file, DirectoryDetailsForSavingBetweenMethodsDto directoryDetails) {
        userDir = System.getProperty("user.dir");

        String baseDirectory = userDir + File.separator + "logistic" + File.separator + "photos";

        if (!checkIsAvailablePath(baseDirectory)) {
            createPath(baseDirectory);
        }

        String fullPath = baseDirectory + File.separator + getFileBodyNameForSaving(file, directoryDetails);

        directoryDetails.setFullPath(fullPath);

        return directoryDetails;

    }

    private String getFileBodyNameForSaving(MultipartFile file, DirectoryDetailsForSavingBetweenMethodsDto directoryDetails) {
        String fileName = UUID.randomUUID().toString();
        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        directoryDetails.setFileName(fileName);
        directoryDetails.setFileExtension(fileExtension);
        return fileName + '.' + fileExtension;
    }

    private void createPath(String baseDirectory) {
        File directory = new File(baseDirectory);

        if (!directory.mkdirs()) {
            log.info("Failed to create directory: " + baseDirectory);
            throw new RuntimeException("Cannot save");
        }

    }

    private boolean checkIsAvailablePath(String basePath) {
        File path = new File(basePath);
        if (!path.exists()) {
            return false;
        }
        return true;
    }
}
