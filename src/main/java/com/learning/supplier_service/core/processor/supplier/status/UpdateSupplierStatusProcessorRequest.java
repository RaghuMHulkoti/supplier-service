package com.learning.supplier_service.core.processor.supplier.status;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import com.learning.supplier_service.core.processor.ProcessorRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSupplierStatusProcessorRequest implements ProcessorRequest {
    private UUID supplierId;
    private OnboardingStatus newStatus;
}
