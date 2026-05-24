package com.learning.supplier_service.core.usecase.warehouse.update;

import com.learning.supplier_service.core.usecase.UsecaseRequest;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateWarehouseUsecaseRequest(
        UUID supplierId,
        UUID warehouseId,
        String name,
        String addressLine1,
        String addressLine2,
        String city,
        String state,
        String postalCode,
        String country,
        BigDecimal geoLat,
        BigDecimal geoLng
) implements UsecaseRequest {
}
