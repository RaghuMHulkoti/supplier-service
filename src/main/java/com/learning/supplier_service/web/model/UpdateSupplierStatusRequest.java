package com.learning.supplier_service.web.model;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateSupplierStatusRequest(
        @NotNull(message = "Status is required")
        OnboardingStatus status
) {
}
