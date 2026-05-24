package com.learning.supplier_service.core.processor.warehouse.list;

import com.learning.supplier_service.core.processor.ProcessorResponse;
import com.learning.supplier_service.gateway.mysql.wareHouse.entity.SupplierWarehouseEntity;

import java.util.List;

public record ListWarehousesProcessorResponse(List<SupplierWarehouseEntity> warehouses) implements ProcessorResponse {
}
