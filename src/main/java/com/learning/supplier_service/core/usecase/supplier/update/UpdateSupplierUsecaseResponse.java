package com.learning.supplier_service.core.usecase.supplier.update;

import com.learning.supplier_service.core.usecase.UsecaseResponse;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;

/**
 * Response for supplier update operations.
 * Always returns the updated SupplierEntity regardless of which fields were updated.
 */
public record UpdateSupplierUsecaseResponse(SupplierEntity supplier) implements UsecaseResponse {
}
