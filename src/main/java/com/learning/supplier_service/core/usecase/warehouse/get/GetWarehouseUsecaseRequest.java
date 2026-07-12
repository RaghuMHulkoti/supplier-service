package com.learning.supplier_service.core.usecase.warehouse.get;

import com.learning.supplier_service.core.usecase.UsecaseRequest;

import java.util.UUID;

public record GetWarehouseUsecaseRequest(UUID supplierId, UUID warehouseId) implements UsecaseRequest {
}
