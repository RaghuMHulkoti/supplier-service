package com.learning.supplier_service.core.processor.supplier.update;

import com.learning.supplier_service.core.processor.ProcessorResponse;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;

public record UpdateSupplierProcessorResponse(SupplierEntity supplier) implements ProcessorResponse {
}
