package com.learning.supplier_service.core.usecase.warehouse.status;

import com.learning.supplier_service.core.usecase.UsecaseRequest;

import java.util.UUID;

public record ToggleWarehouseStatusUsecaseRequest(
        UUID supplierId,
        UUID warehouseId,
        boolean active
) implements UsecaseRequest {
}
