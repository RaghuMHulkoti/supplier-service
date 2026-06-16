package com.learning.supplier_service.core.processor.supplier.update;

import com.learning.supplier_service.core.domain.OnboardingStatus;
import com.learning.supplier_service.core.processor.ProcessorResponse;
import com.learning.supplier_service.gateway.mysql.supplier.entity.SupplierEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Unified response supporting both supplier field update and status update operations.
 * For supplier info update: supplier field contains the updated supplier
 * For status update: supplierId and onboardingStatus fields contain the update result
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSupplierProcessorResponse implements ProcessorResponse {
    private SupplierEntity supplier;
    private UUID supplierId;
    private OnboardingStatus onboardingStatus;

    /**
     * Creates a response for supplier info update
     */
    public static UpdateSupplierProcessorResponse ofUpdate(SupplierEntity supplier) {
        return UpdateSupplierProcessorResponse.builder()
                .supplier(supplier)
                .build();
    }

    /**
     * Creates a response for status update
     */
    public static UpdateSupplierProcessorResponse ofStatusUpdate(UUID supplierId, OnboardingStatus status) {
        return UpdateSupplierProcessorResponse.builder()
                .supplierId(supplierId)
                .onboardingStatus(status)
                .build();
    }

    /**
     * Check if this is a supplier info update response
     */
    public boolean isInfoUpdate() {
        return supplier != null && onboardingStatus == null;
    }

    /**
     * Check if this is a status update response
     */
    public boolean isStatusUpdate() {
        return onboardingStatus != null && supplier == null;
    }
}
