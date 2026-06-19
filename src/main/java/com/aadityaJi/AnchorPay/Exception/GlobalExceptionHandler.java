package com.aadityaJi.AnchorPay.Exception;

import com.aadityaJi.AnchorPay.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<?> handlePasswordMismatch(PasswordMismatchException e){
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .success(false)
                        .message(e.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyUsedEmailException.class)
    public ResponseEntity<?> handleAlreadyUsedEmail(AlreadyUsedEmailException e){
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .success(false)
                        .message(e.getMessage())
                        .build(),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException e){
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .success(false)
                        .message(e.getMessage())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
