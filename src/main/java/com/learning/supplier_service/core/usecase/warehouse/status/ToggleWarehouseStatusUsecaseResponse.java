package com.learning.supplier_service.core.usecase.warehouse.status;

import com.learning.supplier_service.core.usecase.UsecaseResponse;

import java.util.UUID;

public record ToggleWarehouseStatusUsecaseResponse(UUID warehouseId, boolean active) implements UsecaseResponse {
}
