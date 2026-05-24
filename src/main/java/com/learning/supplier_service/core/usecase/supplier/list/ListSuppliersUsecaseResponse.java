package com.learning.supplier_service.core.usecase.supplier.list;

import com.learning.supplier_service.core.usecase.UsecaseResponse;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import org.springframework.data.domain.Page;

public record ListSuppliersUsecaseResponse(Page<SupplierEntity> suppliers) implements UsecaseResponse {
}
