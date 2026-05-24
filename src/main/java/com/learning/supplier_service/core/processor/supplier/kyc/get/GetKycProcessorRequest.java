package com.learning.supplier_service.core.processor.supplier.kyc.get;

import com.learning.supplier_service.core.processor.ProcessorRequest;

import java.util.UUID;

public record GetKycProcessorRequest(UUID supplierId) implements ProcessorRequest {
}
