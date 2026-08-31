package com.pratham.devpilot.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pratham.devpilot.common.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler(DuplicateResourceException.class)
        public ResponseEntity<ApiResponse<Void>> handleDuplicateResourceException(DuplicateResourceException ex) {
                ApiResponse<Void> response = ApiResponse.<Void>builder()
                                .success(false)
                                .message(ex.getMessage())
                                .data(null)
                                .build();
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
                        MethodArgumentNotValidException exception) {

                Map<String, String> errors = new HashMap<>();

                exception.getBindingResult().getFieldErrors()
                                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

                ApiResponse<Map<String, String>> response = ApiResponse.<Map<String, String>>builder()
                                .success(false)
                                .message("Validation failed")
                                .data(errors)
                                .build();

                return ResponseEntity.badRequest().body(response);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException ex) {
                ApiResponse<Void> response = ApiResponse.<Void>builder()
                                .success(false)
                                .message(ex.getMessage())
                                .data(null)
                                .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
}
