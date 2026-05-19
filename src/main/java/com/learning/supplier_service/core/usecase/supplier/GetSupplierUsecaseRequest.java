package com.learning.supplier_service.core.usecase.supplier;

import com.learning.supplier_service.core.usecase.UsecaseRequest;

import java.util.UUID;

public record GetSupplierUsecaseRequest(UUID supplierId) implements UsecaseRequest {
}

