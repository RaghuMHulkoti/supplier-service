package com.learning.supplier_service.core.processor.warehouse.update;

import com.learning.supplier_service.core.processor.ProcessorResponse;
import com.learning.supplier_service.gateway.mysql.wareHouse.entity.SupplierWarehouseEntity;

public record UpdateWarehouseProcessorResponse(SupplierWarehouseEntity warehouse) implements ProcessorResponse {
}
