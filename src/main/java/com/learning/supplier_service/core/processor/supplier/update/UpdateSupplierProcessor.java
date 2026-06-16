package com.learning.supplier_service.core.processor.supplier.update;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.supplier.update.UpdateSupplierUseCase;
import com.learning.supplier_service.core.usecase.supplier.update.UpdateSupplierUsecaseRequest;
import org.springframework.stereotype.Component;

/**
 * Unified processor supporting supplier PATCH updates.
 * All supplied fields are forwarded so the service can update only the values present
 * in the request, including status when it is provided.
 */
@Component
public class UpdateSupplierProcessor implements Processor<UpdateSupplierProcessorRequest, UpdateSupplierProcessorResponse> {

    private final UpdateSupplierUseCase updateSupplierUseCase;

    public UpdateSupplierProcessor(UpdateSupplierUseCase updateSupplierUseCase) {
        this.updateSupplierUseCase = updateSupplierUseCase;
    }

    @Override
    public UpdateSupplierProcessorResponse process(UpdateSupplierProcessorRequest request) {
        var usecaseRequest = UpdateSupplierUsecaseRequest.builder()
                .supplierId(request.getSupplierId())
                .legalName(request.getLegalName())
                .displayName(request.getDisplayName())
                .phone(request.getPhone())
                .taxId(request.getTaxId())
                .commissionPct(request.getCommissionPct())
                .newStatus(request.getNewStatus())
                .build();
        var usecaseResponse = updateSupplierUseCase.execute(usecaseRequest);
        return UpdateSupplierProcessorResponse.ofUpdate(usecaseResponse.supplier());
    }
}
