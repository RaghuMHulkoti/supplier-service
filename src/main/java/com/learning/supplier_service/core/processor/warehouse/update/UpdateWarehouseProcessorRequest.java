package com.learning.supplier_service.core.processor.warehouse.update;

import com.learning.supplier_service.core.processor.ProcessorRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWarehouseProcessorRequest implements ProcessorRequest {
    private UUID supplierId;
    private UUID warehouseId;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private BigDecimal geoLat;
    private BigDecimal geoLng;
}
