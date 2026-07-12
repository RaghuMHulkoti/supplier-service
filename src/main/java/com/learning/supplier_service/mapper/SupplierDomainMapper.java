package com.learning.supplier_service.mapper;

import com.learning.supplier_service.core.domain.Supplier;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;

public final class SupplierDomainMapper {

    private SupplierDomainMapper() {
    }

    public static Supplier toDomain(SupplierEntity entity) {
        return Supplier.builder()
                .id(entity.getId())
                .legalName(entity.getLegalName())
                .displayName(entity.getDisplayName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .taxId(entity.getTaxId())
                .country(entity.getCountry())
                .onboardingStatus(entity.getOnboardingStatus())
                .rating(entity.getRating())
                .commissionPct(entity.getCommissionPct())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
