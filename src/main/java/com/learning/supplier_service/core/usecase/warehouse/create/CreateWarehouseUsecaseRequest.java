package com.learning.supplier_service.core.usecase.warehouse.create;

import com.learning.supplier_service.core.usecase.UsecaseRequest;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
public class CreateWarehouseUsecaseRequest implements UsecaseRequest {

    private final UUID supplierId;

    private final String name;
    private final String code;

    private final String addressLine1;
    private final String addressLine2;

    private final String city;
    private final String state;
    private final String postalCode;
    private final String country;

    private final BigDecimal geoLat;
    private final BigDecimal geoLng;
}