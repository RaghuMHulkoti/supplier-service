package com.learning.supplier_service.core.usecase.supplier.update;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import com.learning.supplier_service.core.usecase.UsecaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Request for updating supplier fields and/or status.
 * Implements PATCH semantics: only non-null fields will be updated.
 * 
 * All fields are optional. Provide only the fields you want to update.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSupplierUsecaseRequest implements UsecaseRequest {
    private UUID supplierId;        // Required
    
    // Optional fields - only provided fields will be updated
    private String legalName;
    private String displayName;
    private String phone;
    private String taxId;
    private BigDecimal commissionPct;
    private OnboardingStatus newStatus;
}
