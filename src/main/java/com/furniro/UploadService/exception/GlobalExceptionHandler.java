package com.furniro.UploadService.exception;

import com.furniro.UploadService.dto.API.AType;
import com.furniro.UploadService.dto.API.ErrorType;
import com.furniro.UploadService.utils.UploadErrorCode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UploadException.class)
    public ResponseEntity<AType> handleUserException(UploadException ex) {

        UploadErrorCode code = ex.getErrorCode();

        AType error = ErrorType.builder()
                .code(code.getCode())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.valueOf(code.getCode()));
    }

}
