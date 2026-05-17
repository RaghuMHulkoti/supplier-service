package com.learning.supplier_service.core.usecase.supplier;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import com.learning.supplier_service.core.usecase.UsecaseResponse;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record CreateSupplierUsecaseResponse(
        UUID supplierId,
        String legalName,
        String displayName,
        String email,
        OnboardingStatus onboardingStatus,
        BigDecimal commissionPct,
        OffsetDateTime createdAt
) implements UsecaseResponse {
}
