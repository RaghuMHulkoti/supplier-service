package com.learning.supplier_service.core.processor.warehouse.status;

import com.learning.supplier_service.core.processor.ProcessorRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToggleWarehouseStatusProcessorRequest implements ProcessorRequest {
    private UUID supplierId;
    private UUID warehouseId;
    private boolean active;
}
