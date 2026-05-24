package com.learning.supplier_service.core.usecase.supplier.status;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import com.learning.supplier_service.core.usecase.UsecaseRequest;

import java.util.UUID;

public record UpdateSupplierStatusUsecaseRequest(
        UUID supplierId,
        OnboardingStatus newStatus
) implements UsecaseRequest {
}
