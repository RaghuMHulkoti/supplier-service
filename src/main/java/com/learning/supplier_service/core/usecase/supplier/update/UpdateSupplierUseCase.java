package com.learning.supplier_service.core.usecase.supplier.update;

import com.learning.supplier_service.core.service.supplier.update.UpdateSupplierService;
import com.learning.supplier_service.core.service.supplier.update.UpdateSupplierServiceRequest;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Unified use case for updating supplier fields and/or status.
 * Supports true PATCH semantics: only updates fields that are provided.
 */
@Component
public class UpdateSupplierUseCase implements Usecase<UpdateSupplierUsecaseRequest, UpdateSupplierUsecaseResponse> {

    private final UpdateSupplierService updateSupplierService;

    public UpdateSupplierUseCase(UpdateSupplierService updateSupplierService) {
        this.updateSupplierService = updateSupplierService;
    }

    @Override
    @Transactional
    public UpdateSupplierUsecaseResponse execute(UpdateSupplierUsecaseRequest request) {
        var serviceRequest = new UpdateSupplierServiceRequest(
                request.getSupplierId(),
                request.getLegalName(),
                request.getDisplayName(),
                request.getPhone(),
                request.getTaxId(),
                request.getCommissionPct(),
                request.getNewStatus()
        );
        
        var serviceResponse = updateSupplierService.execute(serviceRequest);
        return new UpdateSupplierUsecaseResponse(serviceResponse.supplier());
    }
}
