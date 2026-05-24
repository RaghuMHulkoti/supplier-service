package com.learning.supplier_service.core.processor.supplier.list;

import com.learning.supplier_service.core.processor.ProcessorResponse;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import org.springframework.data.domain.Page;

public record ListSuppliersProcessorResponse(Page<SupplierEntity> suppliers) implements ProcessorResponse {
}
