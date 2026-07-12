package com.learning.supplier_service.core.usecase.supplier;

import com.learning.supplier_service.core.usecase.UsecaseResponse;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;

public record GetSupplierUsecaseResponse(SupplierEntity supplier) implements UsecaseResponse {
}

