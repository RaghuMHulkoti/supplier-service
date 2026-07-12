package com.learning.supplier_service.core.usecase.warehouse;

import com.learning.supplier_service.core.usecase.UsecaseResponse;
import com.learning.supplier_service.gateway.mysql.wareHouse.entity.SupplierWarehouseEntity;

import java.util.List;

public record ListWarehouseUsecaseResponse(List<SupplierWarehouseEntity> warehouses) implements UsecaseResponse {
}

