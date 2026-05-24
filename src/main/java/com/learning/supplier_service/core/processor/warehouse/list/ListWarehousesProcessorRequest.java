package com.learning.supplier_service.core.processor.warehouse.list;

import com.learning.supplier_service.core.processor.ProcessorRequest;

import java.util.UUID;

public record ListWarehousesProcessorRequest(UUID supplierId) implements ProcessorRequest {
}
