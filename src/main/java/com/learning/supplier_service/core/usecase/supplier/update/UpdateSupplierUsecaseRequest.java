package com.learning.supplier_service.core.usecase.supplier.update;

import com.learning.supplier_service.core.usecase.UsecaseRequest;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateSupplierUsecaseRequest(
        UUID supplierId,
        String legalName,
        String displayName,
        String phone,
        String taxId,
        BigDecimal commissionPct
) implements UsecaseRequest {
}
