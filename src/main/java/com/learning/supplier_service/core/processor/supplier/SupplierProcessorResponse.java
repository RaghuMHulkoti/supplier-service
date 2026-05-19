package com.learning.supplier_service.core.processor.supplier;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import com.learning.supplier_service.core.processor.ProcessorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierProcessorResponse implements ProcessorResponse {

    private UUID supplierId;
    private boolean success;
}