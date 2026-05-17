package com.learning.supplier_service.exception;

public class DuplicateOfferException extends RuntimeException {
    public DuplicateOfferException(String message) {
        super(message);
    }
}
