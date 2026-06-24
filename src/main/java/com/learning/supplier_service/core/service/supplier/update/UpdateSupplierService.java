package com.learning.supplier_service.core.service.supplier.update;

import com.learning.supplier_service.core.service.Service;
import com.learning.supplier_service.core.service.outbox.OutboxService;
import com.learning.supplier_service.exception.SupplierNotFoundException;
import com.learning.supplier_service.gateway.SupplierGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateSupplierService implements Service<UpdateSupplierServiceRequest, UpdateSupplierServiceResponse> {

    private final SupplierGateway supplierGateway;
    private final OutboxService outboxService;

    public UpdateSupplierService(SupplierGateway supplierGateway, OutboxService outboxService) {
        this.supplierGateway = supplierGateway;
        this.outboxService = outboxService;
    }

    @Override
    public UpdateSupplierServiceResponse execute(UpdateSupplierServiceRequest request) {
        if (request.getSupplierId() == null) {
            throw new IllegalArgumentException("supplierId is required");
        }

        var supplier = supplierGateway.getSupplierById(request.getSupplierId())
                .orElseThrow(() -> new SupplierNotFoundException(request.getSupplierId()));

        // Track if status was updated for event publishing
        boolean statusUpdated = false;

        // Update supplier fields only if provided (non-null)
        if (hasSupplierFieldsToUpdate(request)) {
            supplier.update(
                    request.getLegalName(),
                    request.getDisplayName(),
                    request.getPhone(),
                    request.getTaxId(),
                    request.getCommissionPct()
            );
        }

        // Update status only if provided (non-null)
        if (request.getNewStatus() != null) {
            supplier.updateStatus(request.getNewStatus());
            statusUpdated = true;
        }

        // Save the updated supplier
        var savedSupplier = supplierGateway.saveSupplier(supplier);

        // Publish event only if status was updated
        if (statusUpdated) {
            outboxService.append(savedSupplier.getId(), "SUPPLIER_STATUS_UPDATED",
                    new SupplierStatusUpdatedEvent(savedSupplier.getId(), savedSupplier.getOnboardingStatus().name()));
        }

        return new UpdateSupplierServiceResponse(savedSupplier);
    }


    private boolean hasSupplierFieldsToUpdate(UpdateSupplierServiceRequest request) {
        return request.getLegalName() != null ||
               request.getDisplayName() != null ||
               request.getPhone() != null ||
               request.getTaxId() != null ||
               request.getCommissionPct() != null;
    }

    private record SupplierStatusUpdatedEvent(UUID supplierId, String status) {}
}

