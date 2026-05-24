package com.learning.supplier_service.core.processor.warehouse.status;

import com.learning.supplier_service.core.processor.ProcessorResponse;

import java.util.UUID;

public record ToggleWarehouseStatusProcessorResponse(UUID warehouseId, boolean active) implements ProcessorResponse {
}
