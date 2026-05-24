package com.learning.supplier_service.core.usecase.supplier.list;

import com.learning.supplier_service.core.usecase.Usecase;
import com.learning.supplier_service.gateway.SupplierGateway;
import org.springframework.stereotype.Component;

@Component
public class ListSuppliersUseCase implements Usecase<ListSuppliersUsecaseRequest, ListSuppliersUsecaseResponse> {

    private final SupplierGateway supplierGateway;

    public ListSuppliersUseCase(SupplierGateway supplierGateway) {
        this.supplierGateway = supplierGateway;
    }

    @Override
    public ListSuppliersUsecaseResponse execute(ListSuppliersUsecaseRequest request) {
        return new ListSuppliersUsecaseResponse(supplierGateway.findAll(request.pageable()));
    }
}
