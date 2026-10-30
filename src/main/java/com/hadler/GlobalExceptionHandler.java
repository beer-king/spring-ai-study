package com.hadler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleValidation(
            ConstraintViolationException e) {

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body("AI 응답 검증 실패: " + e.getMessage());
    }
}
