package com.learning.supplier_service.web.response;

import com.learning.supplier_service.gateway.entity.SupplierWarehouseEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record WarehouseResponse(
        UUID warehouseId,
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
        BigDecimal geoLng,
        Boolean active,
        OffsetDateTime createdAt
) {
    public static WarehouseResponse from(SupplierWarehouseEntity warehouse) {
        return new WarehouseResponse(
                warehouse.getId(),
                warehouse.getSupplierId(),
                warehouse.getName(),
                warehouse.getCode(),
                warehouse.getAddressLine1(),
                warehouse.getAddressLine2(),
                warehouse.getCity(),
                warehouse.getState(),
                warehouse.getPostalCode(),
                warehouse.getCountry(),
                warehouse.getGeoLat(),
                warehouse.getGeoLng(),
                warehouse.isActive(),
                warehouse.getCreatedAt()
        );
    }
}
