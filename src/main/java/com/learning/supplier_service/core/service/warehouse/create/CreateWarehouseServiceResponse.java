package com.learning.supplier_service.core.service.warehouse.create;

import com.learning.supplier_service.core.service.ServiceResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@Getter
public class CreateWarehouseServiceResponse implements ServiceResponse {

    private final UUID warehouseId;

    private final UUID supplierId;

    private final String code;

    private final String name;

    private final Boolean active;

    private final OffsetDateTime createdAt;
}
