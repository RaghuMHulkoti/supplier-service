package com.learning.supplier_service.core.processor.warehouse.get;

import com.learning.supplier_service.core.processor.ProcessorResponse;
import com.learning.supplier_service.gateway.mysql.wareHouse.entity.SupplierWarehouseEntity;

public record GetWarehouseProcessorResponse(SupplierWarehouseEntity warehouse) implements ProcessorResponse {
}
