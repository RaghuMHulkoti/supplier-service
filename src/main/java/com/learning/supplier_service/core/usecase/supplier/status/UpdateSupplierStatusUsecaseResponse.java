package com.learning.supplier_service.core.usecase.supplier.status;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import com.learning.supplier_service.core.usecase.UsecaseResponse;

import java.util.UUID;

public record UpdateSupplierStatusUsecaseResponse(
        UUID supplierId,
        OnboardingStatus onboardingStatus
) implements UsecaseResponse {
}
