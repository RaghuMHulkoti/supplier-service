package com.learning.supplier_service.core.service.supplier.update;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import com.learning.supplier_service.core.service.ServiceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Unified request for both supplier field update and status update operations.
 * Use case 1: Update supplier fields (legalName, displayName, phone, taxId, commissionPct)
 * Use case 2: Update supplier status (newStatus)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSupplierServiceRequest implements ServiceRequest {
    private UUID supplierId;

    // Fields for supplier info update
    private String legalName;
    private String displayName;
    private String phone;
    private String taxId;
    private BigDecimal commissionPct;

    // Field for status update
    private OnboardingStatus newStatus;

    /**
     * Creates a request for updating supplier fields
     */
    public static UpdateSupplierServiceRequest forUpdate(
            UUID supplierId,
            String legalName,
            String displayName,
            String phone,
            String taxId,
            BigDecimal commissionPct) {
        return UpdateSupplierServiceRequest.builder()
                .supplierId(supplierId)
                .legalName(legalName)
                .displayName(displayName)
                .phone(phone)
                .taxId(taxId)
                .commissionPct(commissionPct)
                .build();
    }

    /**
     * Creates a request for updating supplier status
     */
    public static UpdateSupplierServiceRequest forStatusUpdate(UUID supplierId, OnboardingStatus newStatus) {
        return UpdateSupplierServiceRequest.builder()
                .supplierId(supplierId)
                .newStatus(newStatus)
                .build();
    }

    /**
     * Check if this is a supplier info update request
     */
    public boolean isInfoUpdate() {
        return legalName != null || displayName != null || phone != null ||
               taxId != null || commissionPct != null;
    }

    /**
     * Check if this is a status update request
     */
    public boolean isStatusUpdate() {
        return newStatus != null;
    }
}

