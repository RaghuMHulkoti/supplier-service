package com.learning.supplier_service.core.usecase.supplier.status;

import com.learning.supplier_service.core.service.supplier.update.UpdateSupplierService;
import com.learning.supplier_service.core.service.supplier.update.UpdateSupplierServiceRequest;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Use case for updating supplier status.
 * This use case delegates to the unified UpdateSupplierService which handles both
 * supplier field update and status update scenarios.
 * 
 * Note: This is now a wrapper around UpdateSupplierService for backward compatibility.
 * New code should use UpdateSupplierUseCase directly with a populated update request.
 */
@Component
public class UpdateSupplierStatusUseCase implements Usecase<UpdateSupplierStatusUsecaseRequest, UpdateSupplierStatusUsecaseResponse> {

    private final UpdateSupplierService updateSupplierService;

    public UpdateSupplierStatusUseCase(UpdateSupplierService updateSupplierService) {
        this.updateSupplierService = updateSupplierService;
    }

    @Override
    @Transactional
    public UpdateSupplierStatusUsecaseResponse execute(UpdateSupplierStatusUsecaseRequest request) {
        var serviceRequest = UpdateSupplierServiceRequest.forStatusUpdate(request.supplierId(), request.newStatus());
        var serviceResponse = updateSupplierService.execute(serviceRequest);
        return new UpdateSupplierStatusUsecaseResponse(serviceResponse.supplier().getId(), serviceResponse.supplier().getOnboardingStatus());
    }
}
