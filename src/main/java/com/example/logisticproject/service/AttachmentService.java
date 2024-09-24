package com.example.logisticproject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class AttachmentService {

    @Value("${file.base-path}")
    private String basePath;



    public void upload(MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }


        String fullPath = checkDirectoryAndOtherAndGetFullPath(file);

//
//        try {
//            file.transferTo(new File(fullPath));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }

    private String checkDirectoryAndOtherAndGetFullPath(MultipartFile file) {

        String fileName = UUID.randomUUID().toString();
        String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);

        String fullPath;

        if (basePath==null || basePath.equals("")) {


            basePath = System.getProperty("user.dir");




            fullPath = basePath+File.separator+"logistic"+File.separator+"photos"+File.separator+fileName+'.'+fileExtension;
        }

        else {

            if (checkIsAvailablePath(basePath)) {
                fullPath = basePath+(!basePath.endsWith(File.separator)?File.separator:"")+fileName+'.'+fileExtension;
            }
            else {
                throw new RuntimeException("Path is not available");
            }

        }

        System.out.println("fullPath = " + fullPath);
        return fullPath;
    }

    private boolean checkIsAvailablePath(String basePath) {
        File file = new File(basePath);
        if (!file.exists()) {
            return false;
        }
        return true;
    }
}
