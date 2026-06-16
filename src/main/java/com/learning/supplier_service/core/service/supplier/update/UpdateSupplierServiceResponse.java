package com.learning.supplier_service.core.service.supplier.update;

import com.learning.supplier_service.core.service.ServiceResponse;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;

/**
 * Unified response for supplier update operations.
 * Always returns the updated SupplierEntity regardless of which fields were updated.
 */
public record UpdateSupplierServiceResponse(SupplierEntity supplier) implements ServiceResponse {
}

