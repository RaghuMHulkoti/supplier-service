package com.learning.supplier_service.core.usecase.warehouse.get;

import com.learning.supplier_service.core.usecase.UsecaseResponse;
import com.learning.supplier_service.gateway.mysql.wareHouse.entity.SupplierWarehouseEntity;

public record GetWarehouseUsecaseResponse(SupplierWarehouseEntity warehouse) implements UsecaseResponse {
}
