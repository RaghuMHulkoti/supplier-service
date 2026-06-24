package com.learning.supplier_service.web.model;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateSupplierRequest(

        UUID supplierId,

        @Size(max = 255, message = "Legal name must be at most 255 characters")
        String legalName,

        @Size(max = 160, message = "Display name must be at most 160 characters")
        String displayName,

        @Size(max = 20, message = "Phone must be at most 20 characters")
        String phone,

        @Size(max = 40, message = "Tax ID must be at most 40 characters")
        String taxId,

        @DecimalMin(value = "0.00", inclusive = true, message = "Commission percentage cannot be negative")
        BigDecimal commissionPct,

        OnboardingStatus status
) {
    @AssertTrue(message = "At least one supplier field or status must be provided")
    public boolean hasAtLeastOneField() {
        return legalName != null || displayName != null || phone != null || taxId != null || commissionPct != null || status != null;
    }
}
