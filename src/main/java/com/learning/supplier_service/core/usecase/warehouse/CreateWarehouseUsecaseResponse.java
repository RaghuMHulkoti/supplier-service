package com.learning.supplier_service.core.usecase.warehouse;

import com.learning.supplier_service.core.usecase.UsecaseResponse;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CreateWarehouseUsecaseResponse(
        UUID warehouseId,
        UUID supplierId,
        String code,
        String name,
        Boolean active,
        OffsetDateTime createdAt
) implements UsecaseResponse {
}
