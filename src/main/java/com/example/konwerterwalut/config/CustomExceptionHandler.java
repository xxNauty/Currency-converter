package com.example.konwerterwalut.config;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleException(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(500);
        problemDetail.setType(URI.create(ex.getClass().getSimpleName()));
        problemDetail.setTitle("An error occurred");
        problemDetail.setDetail(ex.getMessage());

        return ResponseEntity.of(problemDetail).build();
    }
}
