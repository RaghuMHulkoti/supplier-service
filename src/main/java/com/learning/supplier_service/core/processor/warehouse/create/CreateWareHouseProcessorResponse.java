package com.learning.supplier_service.core.processor.warehouse.create;

import com.learning.supplier_service.core.processor.ProcessorResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
public class CreateWareHouseProcessorResponse implements ProcessorResponse {
    private final UUID warehouseId;

    private final UUID supplierId;

    private final String code;

    private final String name;

    private final Boolean active;

    private final OffsetDateTime createdAt;
}
