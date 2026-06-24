package com.learning.supplier_service.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

    private UUID id;
    private UUID supplierId;
    private String code;
    private String name;
    private String country;
    private String city;
    private String addressLine1;
    private String addressLine2;
    private String postalCode;
    private Boolean active;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}