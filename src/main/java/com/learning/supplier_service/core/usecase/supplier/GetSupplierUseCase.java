package com.learning.supplier_service.core.usecase.supplier;

import com.learning.supplier_service.core.service.supplier.create.CreateSupplierService;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;

@Component
public class GetSupplierUseCase implements Usecase<GetSupplierUsecaseRequest, GetSupplierUsecaseResponse> {

    private final CreateSupplierService createSupplierService;

    public GetSupplierUseCase(CreateSupplierService createSupplierService) {
        this.createSupplierService = createSupplierService;
    }

    @Override
    public GetSupplierUsecaseResponse execute(GetSupplierUsecaseRequest request) {
        return new GetSupplierUsecaseResponse(createSupplierService.getRequired(request.supplierId()));
    }
}
