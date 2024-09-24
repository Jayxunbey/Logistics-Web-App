package com.example.logisticproject.service;

import com.example.logisticproject.dto.between.DirectoryDetailsForSavingBetweenMethodsDto;
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

    @Value("${file.saving.base-directory.path}")
    private String baseDirectoryPath;

    @Value("${file.saving.base-directory.if-cannot-access-the-directory-use-default-directory}")
    private boolean ifcannotAccessTheDirectoryUseDefaultDirectory;

    @Value("${file.saving.base-directory.if-dont-exist-the-directory-create}")
    private boolean ifdontExistTheDirectoryCreate;

    @Value("${file.saving.default-directory.is-enabled}")
    private boolean defaultDirectoryIsEnabled;


    @Value("${file.saving.base-directory.if-cant-save-use-default-directory:false}")
    private boolean ifNotExistSaveDefaultDirectory;


    @Transactional
    public void upload(MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }


        var directoryAndOtherAndGetObjAboutPath = checkDirectoryAndOtherAndGetObjAboutPath(file);


        try {
            file.transferTo(new File(directoryAndOtherAndGetObjAboutPath.getFullPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }





    }

    private DirectoryDetailsForSavingBetweenMethodsDto checkDirectoryAndOtherAndGetObjAboutPath(MultipartFile file) {

        var directoryDetails = new DirectoryDetailsForSavingBetweenMethodsDto();

        String fullPath;

        if (defaultDirectoryIsEnabled) {
            return connectToDefaultDirectory(file, directoryDetails);
        } else {

            if (baseDirectoryPath == null || baseDirectoryPath.isBlank()) {

                if (ifcannotAccessTheDirectoryUseDefaultDirectory) {
                    return connectToDefaultDirectory(file, directoryDetails);
                }

                log.info("is not available directory");
                throw new RuntimeException("Cannot save");

            } else {

                if (!checkIsAvailablePath(baseDirectoryPath)) {
                    if (ifdontExistTheDirectoryCreate) {
                        createPath(baseDirectoryPath);
                    }
                    log.info("is not available directory");
                    throw new RuntimeException("Cannot save");
                }

                fullPath = baseDirectoryPath + (!baseDirectoryPath.endsWith(File.separator) ? File.separator : "") + getFileBodyNameForSaving(file, directoryDetails);
                directoryDetails.setFullPath(fullPath);
                return directoryDetails;

            }
        }
    }

    private DirectoryDetailsForSavingBetweenMethodsDto connectToDefaultDirectory(MultipartFile file, DirectoryDetailsForSavingBetweenMethodsDto directoryDetails) {
        baseDirectoryPath = System.getProperty("user.dir");

        String fullPath = baseDirectoryPath + File.separator + "logistic" + File.separator + "photos" + File.separator + getFileBodyNameForSaving(file, directoryDetails);

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
