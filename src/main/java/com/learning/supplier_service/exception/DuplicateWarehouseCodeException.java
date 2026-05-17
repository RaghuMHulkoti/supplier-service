package com.learning.supplier_service.exception;

public class DuplicateWarehouseCodeException extends RuntimeException {
    public DuplicateWarehouseCodeException(String code) {
        super("Warehouse code already exists for supplier: " + code);
    }
}
