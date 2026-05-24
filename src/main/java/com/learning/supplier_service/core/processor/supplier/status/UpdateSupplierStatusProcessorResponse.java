package com.learning.supplier_service.core.processor.supplier.status;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import com.learning.supplier_service.core.processor.ProcessorResponse;

import java.util.UUID;

public record UpdateSupplierStatusProcessorResponse(
        UUID supplierId,
        OnboardingStatus onboardingStatus
) implements ProcessorResponse {
}
