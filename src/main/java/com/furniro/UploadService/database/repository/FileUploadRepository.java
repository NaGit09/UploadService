package com.furniro.UploadService.database.repository;

import com.furniro.UploadService.database.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
}
