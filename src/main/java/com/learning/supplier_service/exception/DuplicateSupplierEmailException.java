package com.learning.supplier_service.exception;

public class DuplicateSupplierEmailException extends RuntimeException {
    public DuplicateSupplierEmailException(String email) {
        super("Supplier already exists for email: " + email);
    }
}
