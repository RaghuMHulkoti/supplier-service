package com.learning.supplier_service.web.response;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import com.learning.supplier_service.gateway.entity.SupplierEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record SupplierResponse(
        UUID supplierId,
        String legalName,
        String displayName,
        String email,
        String phone,
        String taxId,
        String country,
        OnboardingStatus onboardingStatus,
        BigDecimal rating,
        BigDecimal commissionPct,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
    public static SupplierResponse from(SupplierEntity supplier) {
        return new SupplierResponse(
                supplier.getId(),
                supplier.getLegalName(),
                supplier.getDisplayName(),
                supplier.getEmail(),
                supplier.getPhone(),
                supplier.getTaxId(),
                supplier.getCountry(),
                supplier.getOnboardingStatus(),
                supplier.getRating(),
                supplier.getCommissionPct(),
                supplier.getCreatedAt(),
                supplier.getUpdatedAt()
        );
    }
}
