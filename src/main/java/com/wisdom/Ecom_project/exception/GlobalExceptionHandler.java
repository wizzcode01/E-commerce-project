package com.wisdom.Ecom_project.exception;

import com.wisdom.Ecom_project.dto.ResponseDto.responseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<responseDto<Void>> handleEmailConflicts(EmailAlreadyExistsException email){
       responseDto<Void> response = new responseDto<>(email.getMessage());

       return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
   }

   @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<responseDto<Void>> handleInvalidRefreshToken(InvalidRefreshTokenException refreshToken){
       responseDto<Void> response = new responseDto<>(refreshToken.getMessage());

       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
   }

    @ExceptionHandler(InsufficientWalletBalanceException.class)
    public ResponseEntity<responseDto<Void>> handleInsufficientBalance(InsufficientWalletBalanceException ex) {
        responseDto<Void> response = new responseDto<>(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
