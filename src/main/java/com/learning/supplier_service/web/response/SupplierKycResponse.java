package com.learning.supplier_service.web.response;

import com.learning.supplier_service.core.domain.OnboardingStatus;

import java.util.UUID;

public record SupplierKycResponse(
        UUID supplierId,
        String documentKind,
        String documentUrl,
        OnboardingStatus onboardingStatus
) {
}
