package com.learning.supplier_service.core.usecase.warehouse;

import com.learning.supplier_service.core.usecase.UsecaseRequest;

import java.util.UUID;

public record ListWarehouseUsecaseRequest(UUID supplierId) implements UsecaseRequest {
}

