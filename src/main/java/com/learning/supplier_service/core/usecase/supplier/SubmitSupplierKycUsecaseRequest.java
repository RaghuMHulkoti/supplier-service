package com.learning.supplier_service.core.usecase.supplier;

import com.learning.supplier_service.core.usecase.UsecaseRequest;

import java.util.UUID;

public record SubmitSupplierKycUsecaseRequest(
        UUID supplierId,
        String documentKind,
        String documentUrl
) implements UsecaseRequest {
}
