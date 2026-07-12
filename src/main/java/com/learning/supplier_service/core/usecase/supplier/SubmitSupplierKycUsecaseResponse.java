package com.learning.supplier_service.core.usecase.supplier;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import com.learning.supplier_service.core.usecase.UsecaseResponse;

import java.util.UUID;

public record SubmitSupplierKycUsecaseResponse(
        UUID supplierId,
        String documentKind,
        String documentUrl,
        OnboardingStatus onboardingStatus
) implements UsecaseResponse {
}
