package com.furniro.UploadService.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadReq {
    private MultipartFile file;
    private String uploadedBy;
}