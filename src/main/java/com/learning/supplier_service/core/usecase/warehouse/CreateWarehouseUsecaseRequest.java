package com.learning.supplier_service.core.usecase.warehouse;

import com.learning.supplier_service.core.usecase.UsecaseRequest;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateWarehouseUsecaseRequest(
        UUID supplierId,
        String name,
        String code,
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
