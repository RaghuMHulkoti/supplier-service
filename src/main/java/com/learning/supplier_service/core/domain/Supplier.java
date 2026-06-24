package com.learning.supplier_service.core.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    private UUID id;
    private String legalName;
    private String displayName;
    private String email;
    private String phone;
    private String taxId;
    private String country;
    private OnboardingStatus onboardingStatus;
    private BigDecimal rating;
    private BigDecimal commissionPct;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}