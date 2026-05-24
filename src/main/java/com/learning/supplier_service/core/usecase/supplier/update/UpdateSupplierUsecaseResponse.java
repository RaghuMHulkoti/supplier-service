package com.learning.supplier_service.core.usecase.supplier.update;

import com.learning.supplier_service.core.usecase.UsecaseResponse;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;

public record UpdateSupplierUsecaseResponse(SupplierEntity supplier) implements UsecaseResponse {
}
