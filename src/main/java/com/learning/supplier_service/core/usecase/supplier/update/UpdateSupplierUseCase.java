package com.learning.supplier_service.core.usecase.supplier.update;

import com.learning.supplier_service.core.service.supplier.create.CreateSupplierService;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateSupplierUseCase implements Usecase<UpdateSupplierUsecaseRequest, UpdateSupplierUsecaseResponse> {

    private final CreateSupplierService createSupplierService;

    public UpdateSupplierUseCase(CreateSupplierService createSupplierService) {
        this.createSupplierService = createSupplierService;
    }

    @Override
    @Transactional
    public UpdateSupplierUsecaseResponse execute(UpdateSupplierUsecaseRequest request) {
        var supplier = createSupplierService.getRequired(request.supplierId());
        supplier.update(
                request.legalName(),
                request.displayName(),
                request.phone(),
                request.taxId(),
                request.commissionPct()
        );
        return new UpdateSupplierUsecaseResponse(createSupplierService.save(supplier));
    }
}
