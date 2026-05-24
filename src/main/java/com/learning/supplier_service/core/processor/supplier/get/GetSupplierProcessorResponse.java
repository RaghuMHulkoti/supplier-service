package com.learning.supplier_service.core.processor.supplier.get;

import com.learning.supplier_service.core.processor.ProcessorResponse;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;

public record GetSupplierProcessorResponse(SupplierEntity supplier) implements ProcessorResponse {
}
