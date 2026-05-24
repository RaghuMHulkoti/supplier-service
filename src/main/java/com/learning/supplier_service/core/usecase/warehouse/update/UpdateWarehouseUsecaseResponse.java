package com.learning.supplier_service.core.usecase.warehouse.update;

import com.learning.supplier_service.core.usecase.UsecaseResponse;
import com.learning.supplier_service.gateway.mysql.wareHouse.entity.SupplierWarehouseEntity;

public record UpdateWarehouseUsecaseResponse(SupplierWarehouseEntity warehouse) implements UsecaseResponse {
}
