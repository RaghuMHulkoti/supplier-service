package com.learning.supplier_service.core.processor.supplier.get;

import com.learning.supplier_service.core.processor.ProcessorRequest;

import java.util.UUID;

public record GetSupplierProcessorRequest(UUID supplierId) implements ProcessorRequest {
}
