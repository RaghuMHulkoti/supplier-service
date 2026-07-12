package com.learning.supplier_service.exception;

import com.learning.supplier_service.web.response.ErrorResponse;
import com.learning.supplier_service.exception.WarehouseNotFoundException;
import com.learning.supplier_service.exception.KycNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> details = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = error instanceof FieldError fieldError
                    ? fieldError.getField()
                    : error.getObjectName();
            details.put(fieldName, error.getDefaultMessage());
        });

        return buildResponse(
                "VALIDATION_ERROR",
                "Request validation failed",
                HttpStatus.BAD_REQUEST,
                details
        );
    }

    @ExceptionHandler({SupplierNotFoundException.class, OfferNotFoundException.class,
            WarehouseNotFoundException.class, KycNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException ex) {
        return buildResponse("RESOURCE_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler({
            DuplicateSupplierEmailException.class,
            DuplicateWarehouseCodeException.class,
            DuplicateOfferException.class
    })
    public ResponseEntity<ErrorResponse> handleConflict(RuntimeException ex) {
        return buildResponse("RESOURCE_CONFLICT", ex.getMessage(), HttpStatus.CONFLICT, null);
    }

    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFound(org.springframework.web.servlet.resource.NoResourceFoundException ex) {
        log.warn("Resource not found: {}", ex.getMessage());
        return buildResponse(
                "RESOURCE_NOT_FOUND",
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                null
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex) {
        log.error("Unexpected supplier service error", ex);
        return buildResponse(
                "INTERNAL_ERROR",
                "An unexpected error occurred. Please contact support.",
                HttpStatus.INTERNAL_SERVER_ERROR,
                null
        );
    }

    private ResponseEntity<ErrorResponse> buildResponse(
            String errorCode,
            String message,
            HttpStatus status,
            Map<String, String> details
    ) {
        return ResponseEntity.status(status).body(new ErrorResponse(
                errorCode,
                message,
                status.value(),
                OffsetDateTime.now(ZoneOffset.UTC),
                details
        ));
    }
}
