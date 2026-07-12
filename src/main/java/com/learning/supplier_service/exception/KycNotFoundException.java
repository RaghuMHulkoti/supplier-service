package com.learning.supplier_service.exception;
import java.util.UUID;
public class KycNotFoundException extends RuntimeException {
    public KycNotFoundException(UUID supplierId) {
        super("KYC record not found for supplier: " + supplierId);
    }
}
