package com.wisdom.Ecom_project.exception;

import com.wisdom.Ecom_project.dto.ResponseDto.responseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<responseDto<Void>> handleEmailConflict(EmailAlreadyExistsException email){
        responseDto
    }
}
