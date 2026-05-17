package com.learning.supplier_service.exception;

import java.util.UUID;

public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(UUID supplierId) {
        super("Supplier not found: " + supplierId);
    }
}
