package com.furniro.UploadService.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.furniro.UploadService.service.UploadService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final UploadService uploadService;

    @Transactional
    @KafkaListener(topics = "upload.active", groupId = "upload-service-group")
    public void listenActive(Map<String, Object> event) {
        Long fileID = (Long) event.get("fileID");
        uploadService.activeImage(fileID);
    }

    @Transactional
    @KafkaListener(topics = "upload.delete", groupId = "upload-service-group")
    public void listenDelete(Map<String, Object> event) {
        Long fileID = (Long) event.get("fileID");
        uploadService.deleteImage(fileID);

    }
}