package com.learning.supplier_service.core.processor.supplier.kyc.submit;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import com.learning.supplier_service.core.processor.ProcessorResponse;

import java.util.UUID;

public record SubmitKycProcessorResponse(
        UUID supplierId,
        String documentKind,
        String documentUrl,
        OnboardingStatus onboardingStatus
) implements ProcessorResponse {
}
