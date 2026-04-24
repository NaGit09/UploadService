package com.furniro.UploadService.service;

import com.furniro.UploadService.database.entity.FileUpload;
import com.furniro.UploadService.database.repository.FileUploadRepository;
import com.furniro.UploadService.dto.API.AType;
import com.furniro.UploadService.dto.API.ApiType;
import com.furniro.UploadService.dto.API.ErrorType;
import com.furniro.UploadService.dto.req.FileUploadReq;
import com.furniro.UploadService.dto.res.CloudinaryResponse;
import com.furniro.UploadService.exception.UploadException;
import com.furniro.UploadService.utils.UploadErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UploadService {

    private final CloudinaryService cloudinaryService;
    private final FileUploadRepository fileUploadRepository;

    public AType uploadImage(FileUploadReq file) {
        // 1. check file infomation
        if (file == null || file.getFile() == null || file.getFile().isEmpty()) {
            return ErrorType.builder()
                    .code(400)
                    .message("File is required")
                    .build();
        }

        // 2. Upload file to cloudinary
        CloudinaryResponse cloudinaryResponse = cloudinaryService.uploadImage(file.getFile());

        // 3. Save file to database
        FileUpload fileUpload = new FileUpload();
        fileUpload.setPublicId(cloudinaryResponse.getPublicId());
        fileUpload.setUrl(cloudinaryResponse.getUrl());
        fileUpload.setUploadedBy(file.getUploadedBy());
        fileUpload.setIsActive(true);

        fileUploadRepository.save(fileUpload);

        // 4. Return result
        return ApiType.builder()
                .code(200)
                .message("File uploaded successfully")
                .data(cloudinaryResponse)
                .build();
    }

    public void activeImage(Long fileID) {
        // 1. check file infomation
        if (fileID == null) {
            log.error("File ID is required !");
            return;
        }
        FileUpload fileUpload = fileUploadRepository
                .findById(fileID)
                .orElseThrow(() -> new UploadException(UploadErrorCode.UPLOAD_NOT_FOUND));
        
        // 2. Active file
        fileUpload.setIsActive(true);
        fileUploadRepository.save(fileUpload);

        // 3. Return result
        log.info("File activated successfully !");
        return;
    }

    public void deleteImage(Long fileID) {
        // 1. check file infomation
        if (fileID == null) {
            log.error("File ID is required !");
            return;
        }

        FileUpload fileUpload = fileUploadRepository
                .findById(fileID)
                .orElseThrow(() -> new UploadException(UploadErrorCode.UPLOAD_NOT_FOUND));

        // 2. Delete file from cloudinary
        cloudinaryService.deleteFile(fileUpload.getPublicId());

        // 3. Delete file from database
        fileUploadRepository.deleteById(fileID);

        // 4. Return result
        log.info("File deleted successfully !");
        return;
    }
    
}
