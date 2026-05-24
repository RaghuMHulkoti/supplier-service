package com.learning.supplier_service.core.processor.warehouse.get;

import com.learning.supplier_service.core.processor.ProcessorRequest;

import java.util.UUID;

public record GetWarehouseProcessorRequest(UUID supplierId, UUID warehouseId) implements ProcessorRequest {
}
